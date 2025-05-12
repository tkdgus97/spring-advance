package com.tkdgus.springadvance.log.logtrace;

import com.tkdgus.springadvance.log.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String msg);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
