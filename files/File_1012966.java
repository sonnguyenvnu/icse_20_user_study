package com.lianglu.weyue.utils.rxhelper;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by LiangLu on 17-12-18.
 * 原�?�:PublishSubject本身作为观察者和被观察者。
 */

public class RxBus {
    private static volatile RxBus sInstance;
    private final PublishSubject<Object> mEventBus = PublishSubject.create();

    public static RxBus getInstance(){
        if (sInstance == null){
            synchronized (RxBus.class){
                if (sInstance == null){
                    sInstance = new RxBus();
                }
            }
        }
        return sInstance;
    }

    /**
     * �?��?事件(post event)
     * @param event : event object(事件的内容)
     */
    public void post(Object event){
        mEventBus.onNext(event);
    }

    /**
     *
     * @param code
     * @param event
     */
    public void post(int code,Object event){
        Message msg = new Message(code,event);
        mEventBus.onNext(msg);
    }

    /**
     * 返回Event的管�?�者,进行对事件的接�?�
     * @return
     */
    public Observable toObservable(){
        return mEventBus;
    }

    /**
     *
     * @param cls :�?�?接�?�到制定的类型
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> cls){
        //ofType起到过滤的作用,确定接�?�的类型
        return mEventBus.ofType(cls);
    }

    public <T> Observable<T> toObservable(int code, Class<T> cls){
        return mEventBus.ofType(Message.class)
                .filter(msg -> msg.code == code && cls.isInstance(msg.event))
                .map( msg -> (T)msg.event);

    }

    class Message{
        int code;
        Object event;

        public Message(int code,Object event){
            this.code = code;
            this.event = event;
        }
    }
}
