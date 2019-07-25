package com.xiaojinzi.componentdemo;

import android.util.Log;

import com.xiaojinzi.component.anno.ConditionalAnno;
import com.xiaojinzi.component.anno.GlobalInterceptorAnno;
import com.xiaojinzi.component.condition.Condition;
import com.xiaojinzi.component.impl.RouterInterceptor;

/**
 * 全局的一个监测的拦截器
 * 使用�?�件注解�?�以让这个拦截器�?�在 debug 的时候生效
 * time   : 2019/02/19
 *
 * @author : xiaojinzi 30212
 */
@GlobalInterceptorAnno
@ConditionalAnno(conditions = MonitorInterceptor.OnCondition.class)
public class MonitorInterceptor implements RouterInterceptor {

    @Override
    public void intercept(Chain chain) throws Exception {
        String uriStr = chain.request().uri.toString();
        Log.d("全局监控拦截器", "uri = " + uriStr);
        chain.proceed(chain.request());
    }

    public static class OnCondition implements Condition {

        @Override
        public boolean matches() {
            return BuildConfig.DEBUG;
        }

    }

}
