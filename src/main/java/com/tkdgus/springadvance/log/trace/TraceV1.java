package com.tkdgus.springadvance.log.trace;

import com.tkdgus.springadvance.log.Trace;
import com.tkdgus.springadvance.log.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TraceV1 {
    private final String START_PREFIX = "-->";
    private final String COMPLETE_PREFIX = "<--";
    private final String EX_PREFIX = "<X-";

    public TraceStatus begin(String msg){
        Trace trace = new Trace();
        Long stTime = System.currentTimeMillis();

        log.info("[{}] {}{}", trace.getId(), addSpace(START_PREFIX, trace.getLevel()), msg);

        return new TraceStatus(msg, trace, stTime);
    }
    public void end(TraceStatus status) {
        complete(status, null);
    }

    public TraceStatus beginSync(Trace beforeTrace, String msg) {
        Trace nxt = beforeTrace.nextTrace();
        Long stTime = System.currentTimeMillis();

        log.info("[{}] {}{}", nxt.getId(), addSpace(START_PREFIX, nxt.getLevel()), msg);

        return new TraceStatus(msg, nxt, stTime);
    }
    public void exception(TraceStatus status, Exception e){
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long endTime = System.currentTimeMillis();
        long result = endTime - status.getStTimeMs();

        Trace t = status.getTrace();

        if (e == null) {
            log.info("[{}] {}{} time={}", t.getId(), addSpace(COMPLETE_PREFIX, t.getLevel()), status.getMsg(),result);
        } else {
            log.info("[{}] {}{} time={} ex={}", t.getId(), addSpace(EX_PREFIX, t.getLevel()), status.getMsg(),result, e.toString());
        }
    }

    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
