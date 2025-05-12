package com.tkdgus.springadvance.config;

import com.tkdgus.springadvance.log.logtrace.LogTrace;
import com.tkdgus.springadvance.log.logtrace.LogTraceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new LogTraceImpl();
    }
}
