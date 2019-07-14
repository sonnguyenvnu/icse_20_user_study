package com.example.jingbin.cloudreader.http.rx;


import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by jingbin on 19/02/25.
 */
public class RxBus {
    /**
     * �?�考网�?�: http://hanhailong.com/2015/10/09/RxBus%E2%80%94%E9%80%9A%E8%BF%87RxJava%E6%9D%A5%E6%9B%BF%E6%8D%A2EventBus/
     * http://www.loongwind.com/archives/264.html
     * https://theseyears.gitbooks.io/android-architecture-journey/content/rxbus.html
     */
    private static volatile RxBus mDefaultInstance;

    private RxBus() {
    }

    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    private final Subject<Object> _bus = PublishSubject.create().toSerialized();

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return _bus;
    }

    /**
     * 根�?�传递的 eventType 类型返回特定类型(eventType)的 被观察者
     *
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return _bus.ofType(eventType);
    }

    /**
     * �??供了一个新的事件,根�?�code进行分�?�
     *
     * @param code 事件code
     * @param o
     */
    public void post(int code, Object o) {
        _bus.onNext(new RxBusBaseMessage(code, o));

    }


    /**
     * 根�?�传递的code和 eventType 类型返回特定类型(eventType)的 被观察者
     * 对于注册了code为0，class为voidMessage的观察者，那么就接收�?到code为0之外的voidMessage。
     *
     * @param code      事件code
     * @param eventType 事件类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final int code, final Class<T> eventType) {
        return _bus.ofType(RxBusBaseMessage.class)
                .filter(new Predicate<RxBusBaseMessage>() {
                    @Override
                    public boolean test(RxBusBaseMessage rxBusBaseMessage) throws Exception {
                        //过滤code和eventType都相�?�的事件
                        return rxBusBaseMessage.getCode() == code && eventType.isInstance(rxBusBaseMessage.getObject());
                    }
                }).map(new Function<RxBusBaseMessage, Object>() {
                    @Override
                    public Object apply(RxBusBaseMessage rxBusBaseMessage) throws Exception {
                        return rxBusBaseMessage.getObject();
                    }
                }).cast(eventType);
    }

    /**
     * 判断是�?�有订阅者
     */
    public boolean hasObservers() {
        return _bus.hasObservers();
    }

}
