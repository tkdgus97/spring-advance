package com.tkdgus.springadvance.v1;

import com.tkdgus.springadvance.log.TraceStatus;
import com.tkdgus.springadvance.log.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV1 {
    private final LogTrace logTrace;

    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository." + Thread.currentThread().getStackTrace()[1].getMethodName());
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("exception");
            }

            sleep(1000);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
