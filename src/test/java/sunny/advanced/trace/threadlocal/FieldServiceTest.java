package sunny.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sunny.advanced.trace.threadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {
    private final FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); // 2초를 지연하기 때문에 동시성 발생 X. FieldService는 1초 지연 로직이기 때문.
        sleep(200); // 1초보다 짧게 지연시키기 때문에 동시성 발생 O.
        threadB.start();


        sleep(3000); //메인 쓰레드 종료 대기.
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
