package cc.mrbird.demo.domain;

/**
 * @author MrBird
 */
public class User {

    public User() {
        System.out.println("调用无�?�构造器创建User");
    }

    public void init() {
        System.out.println("�?始化User");
    }

    public void destory() {
        System.out.println("销�?User");
    }
}
