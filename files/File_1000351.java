package org.nutz.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.impl.DaoExecutor;
import org.nutz.dao.sql.DaoStatement;
import org.nutz.dao.sql.SqlContext;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Dao执行拦截器链.
 * 
 * @author wendal
 * @see org.nutz.dao.impl.interceptor.DaoLogInterceptor
 * @see org.nutz.dao.impl.interceptor.DaoTimeInterceptor
 */
public class DaoInterceptorChain implements ConnCallback {

    private static final Log log = Logs.get();

    protected int autoTransLevel;

    protected Connection connection;

    protected int current = 0;

    protected DaoStatement daoStatement;

    protected DaoExecutor executor;

    protected List<DaoInterceptor> interceptors = new ArrayList<DaoInterceptor>();

    protected int updateCount;

    protected DaoStatement[] sts;

    protected String id;

    /**
     * 新建一个DaoInterceptorChain.
     * 
     * @param sts
     *            将�?进行的Dao�?作(�?一定是SQL�?作,有�?�能是EL)
     */
    public DaoInterceptorChain(DaoStatement... sts) {
        this.sts = sts;
        id = R.UU32();
    }

    /**
     * 继续下一个拦截器,如果已�?是最�?�一个拦截器,那么执行executor.exec
     * 
     * @return 本对象,用于链�?�?作
     * @throws Exception
     */
    public DaoInterceptorChain doChain() throws DaoException {
        if (hasNext()) {
            DaoInterceptor interceptor = next();
            current++;
            interceptor.filter(this);
        } else {
            executor.exec(getConnection(), getDaoStatement());
            updateCount += getDaoStatement().getUpdateCount();
        }
        return this;
    }

    /**
     * 获�?�当�?自动事务级别,DaoRunner中使用强制事务时会使用之.拦截器�?能修改,�?�使修改也�?会生效
     * 
     * @return 当�?自动(强制)事务级别
     */
    public int getAutoTransLevel() {
        return autoTransLevel;
    }

    /**
     * 当�?执行的DaoStatement
     * 
     * @return 当�?执行的DaoStatement
     */
    public DaoStatement getDaoStatement() {
        return daoStatement;
    }

    /**
     * 全部DaoStatement,�?�能�?止一�?�
     * 
     * @return 全部DaoStatement
     */
    public DaoStatement[] getDaoStatements() {
        return sts;
    }

    /**
     * 拦截器列表(暂�?开放修改)
     * 
     * @return 全体拦截器列表
     */
    public List<DaoInterceptor> getInterceptors() {
        return interceptors;
    }

    /**
     * 更新总数,用于DaoSupport(NutDao)获�?�更新总数.
     * 
     * @return 更新记录总数
     */
    public int getUpdateCount() {
        return updateCount;
    }

    /**
     * 是�?�还有下一个拦截器
     * 
     * @return true,如果还有拦截器�?执行
     */
    public boolean hasNext() {
        return current < interceptors.size();
    }

    /**
     * 这是DaoExecutor会执行的方法,拦截器内�?�?执行这个方法!! 这里也是拦截器开始生效的地方.
     */
    public void invoke(Connection conn) throws Exception {
        for (DaoStatement st : sts) {
            if (st == null) {
                if (log.isInfoEnabled())
                    log.info("Found a null DaoStatement(SQL), ingore it ~~");
                continue;
            }
            current = 0;
            daoStatement = st;
            this.connection = conn;
            doChain();
        }
    }

    /**
     * 获�?�下一个拦截器. 调用�?必须先调用hasNext进行判断
     * 
     * @return 下一个拦截器
     */
    public DaoInterceptor next() {
        return interceptors.get(current);
    }

    /**
     * 设置强制事务的级别,对拦截器�?�说无�?义.
     * 
     * @param autoTransLevel
     *            与DaoSupport(NutDao)内的值一致
     */
    public void setAutoTransLevel(int autoTransLevel) {
        this.autoTransLevel = autoTransLevel;
    }

    /**
     * 设置当�?拦截器索引. 若设置值大于拦截器列表的大�?,那么效果就等�?�于跳过剩余拦截器,直接执行DaoStatement
     * 
     * @param current
     */
    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * 设置DaoExecutor. 典型应用是在拦截器中替�?��?daocache�??供的DaoExecutor
     * 
     * @param executor
     *            新的DaoExecutor,�?�?�以是null
     */
    public void setExecutor(DaoExecutor executor) {
        this.executor = executor;
    }

    /**
     * 设置新的拦截器列表.
     * 
     * @param interceptors
     *            新的拦截器列表
     */
    public void setInterceptors(List<DaoInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    /**
     * 设置当�?使用的数�?�库连接
     * 
     * @param connection
     *            新的数�?�库连接,�?�?�以是null
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * 获�?�当�?数�?�库连接
     * 
     * @return 当�?数�?�库连接
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 获�?�当�?DaoStatement的上下文,注�?,一个拦截器链�?�能包�?�多个DaoStatement
     * 
     * @return 当�?DaoStatement的上下文
     */
    public SqlContext getSqlContext() {
        return getDaoStatement().getContext();
    }

    /**
     * 拦截器链的id, 为一个uu32识别符.
     * 
     * @return 本拦截器链的id
     */
    public String getId() {
        return id;
    }
    
    /**
     * 替�?�当�?执行的DaoStatement
     * @param daoStatement
     */
    public void setDaoStatement(DaoStatement daoStatement) {
        this.daoStatement = daoStatement;
    }
}
