package com.tkdgus.springadvance.log.trace;

import com.tkdgus.springadvance.log.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraceV1Test {

    @Test
    void begin_end() {
        TraceV1 trace = new TraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

}