package sunny.advanced.trace;

import lombok.Getter;
import java.util.UUID;

@Getter
public class TraceId {
    private final String id;
    private final int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 앞 8개만 ID로 사용할 예정.
        return UUID.randomUUID().toString().substring(0, 8);
    }

    // 로그 찍을 시 ID는 동일하나 level만 하나씩 증가.
    // 애플리케이션 시작 후 메서드 진행 방향을 쉽게 읽기 위해서?라고 난 생각함.
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
