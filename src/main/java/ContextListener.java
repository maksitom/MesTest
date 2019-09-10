import lombok.extern.slf4j.Slf4j;
import mes.TestMes;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {

    @Inject
    private TestMes testMes;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.debug("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("contextDestroyed");
        testMes();
    }

    private void testMes() {
        log.debug("Test mes");
        Map<Future, Future> map = new HashMap<>();
        try {
            for (int i = 0; i < 10; i++) {
                Future<?> future = testMes.getMes().submit(() -> {
                    log.debug("submit");
                });
                map.put(future, future);
                log.debug("Future " + future + " put to map. isCanceled=" + future.isCancelled() + ", isDone=" + future.isDone());
            }
        } catch (RejectedExecutionException ex) {
            log.error("RejectedExecutionException", ex);
        }

        for (Future future : map.values()) {
            log.debug(future + " isCanceled=" + future.isCancelled() + ", isDone=" + future.isDone());
        }
    }

}
