package org.nutz.aop.interceptor;

import java.sql.Connection;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.trans.Trans;

/**
 * �?�以�?�入事务的拦截器
 * <p/>
 * 默认事务级别为 Connection.TRANSACTION_READ_COMMITTED
 * <p/>
 * �?�以在构建拦截器时设置
 * 
 * @author wendal(wendal1985@gmail.com)
 * 
 */
public class TransactionInterceptor implements MethodInterceptor {

    private int level;

    public TransactionInterceptor() {
        this.level = Connection.TRANSACTION_READ_COMMITTED;
    }

    public TransactionInterceptor(int level) {
        this.level = level;
    }

    public void filter(final InterceptorChain chain) throws Throwable {
        try {
            Trans.begin(level);
            chain.doChain();
            Trans.commit();
        }
        catch (Throwable e) {
            Trans.rollback();
            throw e;
        } finally {
            Trans.close();
        }
    }

}
