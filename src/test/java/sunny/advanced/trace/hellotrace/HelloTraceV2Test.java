package sunny.advanced.trace.hellotrace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sunny.advanced.trace.TraceStatus;

class HelloTraceV2Test {
    @Test
    @DisplayName("로그 시작 확인 테스트")
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);

    }

    @Test
    @DisplayName("로그 시작 시 에러 테스트")
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}