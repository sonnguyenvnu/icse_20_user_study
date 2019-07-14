package cc.mrbird.demo.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author MrBird
 */
public class Fish {

    public Fish() {
        System.out.println("调用无�?�构造器创建Fish");
    }

    @PostConstruct
    public void init() {
        System.out.println("�?始化Fish");
    }

    @PreDestroy
    public void destory() {
        System.out.println("销�?Fish");
    }
}
