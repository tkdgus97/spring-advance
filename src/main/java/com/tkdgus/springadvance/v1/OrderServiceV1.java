package com.tkdgus.springadvance.v1;

import com.tkdgus.springadvance.log.TraceStatus;
import com.tkdgus.springadvance.log.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final LogTrace logTrace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin( "OrderService." + Thread.currentThread().getStackTrace()[1].getMethodName());
            orderRepositoryV1.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
