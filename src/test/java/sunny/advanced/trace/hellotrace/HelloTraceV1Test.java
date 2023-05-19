package sunny.advanced.trace.hellotrace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sunny.advanced.trace.TraceStatus;

class HelloTraceV1Test {
    @Test
    @DisplayName("로그 시작 확인 테스트")
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    @DisplayName("로그 시작 시 에러 테스트")
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}