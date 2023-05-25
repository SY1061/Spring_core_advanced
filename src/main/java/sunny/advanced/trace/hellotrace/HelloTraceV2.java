package sunny.advanced.trace.hellotrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sunny.advanced.trace.TraceId;
import sunny.advanced.trace.TraceStatus;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    // 로그 시작하는 메서드.
    // 결과로 현재 로그 상태인 TraceStatus 반환.
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        //로그 출력.
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
                traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    // V2 추가. 처음에는 begin을 사용하나 그 이후에는 beginSync을 사용하여 동일한 traceID가 로그에 찍히게 한다.
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX,
                nextId.getLevel()), message);
        return new TraceStatus(nextId, startTimeMs, message);
    }

    // 정상적으로 진행될 때 출력되는 로그.
    public void end(TraceStatus status) {
        complete(status, null);
    }

    // 에러가 발생했을 때 출력되는 로그.
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if ( e == null ) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
    }

    // Level = 0
    // Level = 1 |-->
    // Level = 2 |  |-->
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }
}
