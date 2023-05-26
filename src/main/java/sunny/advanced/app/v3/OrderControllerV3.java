package sunny.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sunny.advanced.trace.TraceStatus;
import sunny.advanced.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    // http 파라미터 이름과 변수 이름이 같으면 @RequestParam 생략 가능.
    @GetMapping("/v3/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함.
        }
    }
}
