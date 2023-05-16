package sunny.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 로그의 상태 정보를 나타내는 클래스.
@Getter
@AllArgsConstructor
public class TraceStatus {
    private TraceId traceId; // 내부에 트랜잭션ID와 Level을 가지고 있음.
    private Long startTimeMs; // 로그 시작시간.
    private String message; // 시작시 사용되는 메세지이며 종료시에도 동일한 메시지 사용.
}
