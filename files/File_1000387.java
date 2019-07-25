package org.nutz.dao.impl.interceptor;

import org.nutz.dao.DaoException;
import org.nutz.dao.DaoInterceptor;
import org.nutz.dao.DaoInterceptorChain;
import org.nutz.dao.impl.sql.run.NutDaoExecutor;
import org.nutz.dao.sql.DaoStatement;

/**
 * æŠŠDaoStatementä¼˜ç¾Žåœ°æ‰“å?°å‡ºæ?¥.é»˜è®¤å?¯ç”¨.
 * 
 * @author wendal
 * @since 1.r.58
 */
public class DaoLogInterceptor implements DaoInterceptor {

    public void filter(DaoInterceptorChain chain) throws DaoException {
        DaoStatement statement = chain.getDaoStatement();
        if (statement != null) {
            NutDaoExecutor.printSQL(statement);
        }
        chain.doChain();
    }

}
