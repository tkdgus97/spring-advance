package com.tkdgus.springadvance.log.logtrace;

import com.tkdgus.springadvance.log.Trace;
import com.tkdgus.springadvance.log.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTraceImpl implements LogTrace {
    private final String START_PREFIX = "-->";
    private final String COMPLETE_PREFIX = "<--";
    private final String EX_PREFIX = "<X-";

    private ThreadLocal<Trace> traceHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String msg) {
        syncTrace();
        Trace trace = traceHolder.get();
        Long stTime = System.currentTimeMillis();

        log.info("[{}] {}{}", trace.getId(), addSpace(START_PREFIX, trace.getLevel()), msg);

        return new TraceStatus(msg, trace, stTime);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long endTime = System.currentTimeMillis();
        long result = endTime - status.getStTimeMs();
        Trace t = status.getTrace();

        if (e == null) {
            log.info("[{}] {}{} time={}", t.getId(), addSpace(COMPLETE_PREFIX, t.getLevel()), status.getMsg(), result);
        } else {
            log.info("[{}] {}{} time={} ex={}", t.getId(), addSpace(EX_PREFIX, t.getLevel()), status.getMsg(), result, e.toString());
        }

        releaseTrace();
    }

    private void syncTrace() {
        if (traceHolder.get() == null) {
            this.traceHolder.set(new Trace());
        } else {
            this.traceHolder.set(traceHolder.get().nextTrace());
        }
    }

    private void releaseTrace() {
        if (traceHolder.get().getLevel() != 0) {
            traceHolder.set(traceHolder.get().previousTrace());
        } else {
            traceHolder.remove();
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
