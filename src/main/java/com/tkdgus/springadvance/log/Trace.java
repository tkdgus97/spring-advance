package com.tkdgus.springadvance.log;


import lombok.Getter;

import java.util.UUID;

@Getter
public class Trace {
    private String id;
    private int level;

    public Trace() {
        this.id = createId();
        this.level = 0;
    }

    private Trace(String id, int i) {
        this.id = id;
        this.level = i;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0,8);
    }
    
    public Trace nextTrace() {
        return new Trace(id, this.level + 1);
    }

    public Trace previousTrace() {
        return new Trace(id, this.level - 1);
    }
}
