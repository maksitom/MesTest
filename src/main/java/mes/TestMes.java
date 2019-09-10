package mes;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;

@Singleton
@Startup
@Slf4j
@Getter
public class TestMes {

    @Resource(lookup = "concurrent/testMes")
    private ManagedExecutorService mes;

    @PostConstruct
    public void init() {
        log.debug("TestMes init");
    }

    @PreDestroy
    public void destroy() {
        log.debug("TestMes destroy");
    }

}
