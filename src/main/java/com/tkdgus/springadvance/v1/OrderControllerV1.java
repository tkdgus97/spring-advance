package com.tkdgus.springadvance.v1;

import com.tkdgus.springadvance.log.TraceStatus;
import com.tkdgus.springadvance.log.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderControllerV1 {

    private final OrderServiceV1 orderServiceV1;
    private final LogTrace logTrace;

    @GetMapping("/v1")
    public String saveItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin( "OrderController." + Thread.currentThread().getStackTrace()[1].getMethodName());
            orderServiceV1.orderItem(itemId);
            logTrace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
