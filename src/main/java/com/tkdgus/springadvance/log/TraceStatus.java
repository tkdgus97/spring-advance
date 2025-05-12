package com.tkdgus.springadvance.log;

import lombok.Getter;

@Getter
public class TraceStatus {
    private String msg;
    private Trace trace;
    private Long stTimeMs;

    public TraceStatus(String msg, Trace trace, Long stTimeMs) {
        this.msg = msg;
        this.trace = trace;
        this.stTimeMs = stTimeMs;
    }


}
