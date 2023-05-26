package sunny.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sunny.advanced.trace.logtrace.FieldLogTrace;
import sunny.advanced.trace.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
