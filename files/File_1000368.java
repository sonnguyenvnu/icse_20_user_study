package org.nutz.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.DaoInterceptor;
import org.nutz.dao.DaoInterceptorChain;
import org.nutz.dao.DatabaseMeta;
import org.nutz.dao.SqlManager;
import org.nutz.dao.entity.EntityMaker;
import org.nutz.dao.impl.entity.AnnotationEntityMaker;
import org.nutz.dao.impl.interceptor.DaoLogInterceptor;
import org.nutz.dao.impl.interceptor.DaoTimeInterceptor;
import org.nutz.dao.impl.sql.NutPojoMaker;
import org.nutz.dao.impl.sql.run.NutDaoExecutor;
import org.nutz.dao.impl.sql.run.NutDaoRunner;
import org.nutz.dao.jdbc.JdbcExpert;
import org.nutz.dao.jdbc.Jdbcs;
import org.nutz.dao.sql.DaoStatement;
import org.nutz.dao.sql.PojoMaker;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlContext;
import org.nutz.dao.util.Daos;
import org.nutz.lang.Configurable;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Dao 接�?�实现类的一些基础环境
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class DaoSupport implements Configurable {

    private static final Log log = Logs.get();

    /**
     * 给�?类使用的 Dao 的�?行器，用�?��?装事务
     */
    protected DaoRunner runner;

    /**
     * 给�?类使用的 Dao 语�?�执行器，用�?�具体�?行�?一�?�语�?�
     */
    protected DaoExecutor executor;

    /**
     * 给�?类使用数�?��?
     */
    protected DataSource dataSource;

    /**
     * 给�?类使用的数�?�特殊性的�?装
     */
    protected JdbcExpert expert;

    /**
     * 给�?类使用的 PojoStatementMaker 接�?�
     */
    protected PojoMaker pojoMaker;

    /**
     * 给�?类使用的 Entity 获�?�对象
     */
    protected EntityHolder holder;

    /**
     * 数�?�库的�??述
     */
    protected DatabaseMeta meta;

    /**
     * SQL 管�?�接�?�实现类
     */
    protected SqlManager sqlManager;
    
    protected int autoTransLevel = Connection.TRANSACTION_READ_COMMITTED;
    
    protected List<DaoInterceptor> _interceptors;

    public DaoSupport() {
        this.runner = new NutDaoRunner();
        this.executor = new NutDaoExecutor();
        this.setInterceptors(Lang.list((Object)"log"));
    }

    /**
     * @return Sql 管�?�接�?�的实例
     */
    public SqlManager sqls() {
        return sqlManager;
    }

    /**
     * @return 当�?连接的数�?�库的一些�??述数�?�
     */
    public DatabaseMeta meta() {
        return meta;
    }

    /**
     * 设置一个新的 Sql 管�?�接�?�实例
     * 
     * @param sqls
     *            Sql 管�?�接�?�实例
     */
    public void setSqlManager(SqlManager sqls) {
        this.sqlManager = sqls;
        if (sqls != null) {
            int count = sqls.count();
            log.debug("SqlManager Sql count=" + count);
        }
    }

    /**
     * 设置一个新的 Dao �?行器
     * 
     * @param runner
     *            �?行器对象
     */
    public void setRunner(DaoRunner runner) {
        this.runner = runner;
        if (runner instanceof NutDaoRunner) {
        	((NutDaoRunner)runner).setMeta(meta);
        }
    }

    /**
     * 设置一个新的 Dao 语�?�执行器
     * 
     * @param executor
     *            Dao 语�?�执行器对象
     */
    public void setExecutor(DaoExecutor executor) {
        this.executor = executor;
        if (executor instanceof NutDaoExecutor) {
        	((NutDaoExecutor)executor).setMeta(meta);
        	((NutDaoExecutor)executor).setExpert(expert);
        }
    }

    /**
     * 设置一个新的 Pojo 语�?�创建器
     * 
     * @param pojoMaker
     *            Pojo 语�?�创建器
     */
    public void setPojoMaker(PojoMaker pojoMaker) {
        this.pojoMaker = pojoMaker;
    }

    /**
     * @return 当�?的 JDBC 专家类
     */
    public JdbcExpert getJdbcExpert() {
        return expert;
    }

    /**
     * 设置新的数�?��?。
     * <p>
     * 如果有�?的数�?��?需�?你在外部手动关闭
     * 
     * @param ds
     *            数�?��?
     */
    public void setDataSource(DataSource ds) {
        setDataSource(ds,false);
    }
    
    public void setDataSource(DataSource ds,boolean isLazy) {
        if (null != dataSource)
            if (log.isWarnEnabled())
                log.warn("Replaced a running dataSource!");
        dataSource = ds;
        if (expert == null)
            expert = Jdbcs.getExpert(ds);
        log.debug("select expert : " + expert.getClass().getName());
        pojoMaker = new NutPojoMaker(expert);

        meta = new DatabaseMeta();
        final Set<String> keywords = new HashSet<String>(Daos.sql2003Keywords());
        run(new ConnCallback() {
            public void invoke(Connection conn) throws Exception {
                try {
                    DatabaseMetaData dmd = conn.getMetaData();
                    meta.setProductName(dmd.getDatabaseProductName());
                    meta.setVersion(dmd.getDatabaseProductVersion());
                    log.debug("JDBC Driver --> " + dmd.getDriverVersion());
                    log.debug("JDBC Name   --> " + dmd.getDriverName());
                    if (!Strings.isBlank(dmd.getURL()))
                        log.debug("JDBC URL    --> " + dmd.getURL());
                    if (dmd.getDriverName().contains("mariadb") || dmd.getDriverName().contains("sqlite")) {
                        log.warn("Auto-select fetch size to Integer.MIN_VALUE, enable for ResultSet Streaming");
                        SqlContext.DEFAULT_FETCH_SIZE = Integer.MIN_VALUE;
                    }
                    String tmp = dmd.getSQLKeywords();
                    if (tmp != null) {
                        for (String keyword : tmp.split(",")) {
                            keywords.add(keyword.toUpperCase());
                        }
                    }
                    expert.checkDataSource(conn);
                }
                catch (Exception e) {
                    log.info("something wrong when checking DataSource", e);
                }
            }
        });
        if (log.isDebugEnabled())
            log.debug("Database info --> " + meta);
        expert.setKeywords(keywords);

        if(!isLazy)
        {
            holder = new EntityHolder(this.expert, dataSource);
            holder.maker = createEntityMaker();
        }
        setRunner(runner);
        setExecutor(executor);
    }

    public void execute(final Sql... sqls) {
        for (Sql sql : sqls)
            expert.formatQuery(sql);
        _exec(sqls);
    }

    public void run(ConnCallback callback) {
        runner.run(dataSource, callback);
    }

    protected int _exec(final DaoStatement... sts) {
        if (sts != null)
            for (DaoStatement ds : sts) {
                ds.setExpert(expert);
            }
        final DaoInterceptorChain callback = new DaoInterceptorChain(sts);
        callback.setExecutor(executor);
        callback.setAutoTransLevel(autoTransLevel);
        callback.setInterceptors(Collections.unmodifiableList(this._interceptors));
        run(callback);
        // �?�定，返回结果 ^_^
        return callback.getUpdateCount();
    }

    /**
     * �?类�?�以�?写这个类，用�?�扩展�?其他的实体�?置方�?
     * 
     * @return 实体工厂
     */
    protected EntityMaker createEntityMaker() {
        return new AnnotationEntityMaker(dataSource, expert, holder);
    }
    
    public PojoMaker pojoMaker() {
		return pojoMaker;
	}
    
    public void setAutoTransLevel(int autoTransLevel) {
        this.autoTransLevel = autoTransLevel;
    }
    
    public void setInterceptors(List<Object> interceptors) {
        List<DaoInterceptor> list = new LinkedList<DaoInterceptor>();
        for (Object it : interceptors) {
            DaoInterceptor d = makeInterceptor(it);
            if (d != null)
                list.add(d);
        }
        this._interceptors = list;
    }
    
    public void addInterceptor(Object it) {
        DaoInterceptor d = makeInterceptor(it);
        if (d != null) {
            List<DaoInterceptor> list = new LinkedList<DaoInterceptor>(this._interceptors);
            list.add(d);
            this._interceptors = list;
        }
    }
    
    public DaoInterceptor makeInterceptor(Object it) {
        if (it == null)
            return null;
        if (it instanceof String) {
            String itName = it.toString().trim();
            if ("log".equals(itName)) {
                return new DaoLogInterceptor();
            }
            else if ("time".equals(itName)) {
                return new DaoTimeInterceptor();
            } 
            else if (itName.contains(".")) {
                Class<?> klass = Lang.loadClassQuite(itName);
                if (klass == null) {
                    log.warn("no such interceptor name="+itName);
                } else {
                    return (DaoInterceptor) Mirror.me(klass).born();
                }
            } else {
                log.info("unkown interceptor name="+itName);
            }
        }
        else if (it instanceof DaoInterceptor) {
            return (DaoInterceptor) it;
        } else {
            log.info("unkown interceptor -> "+it);
        }
        return null;
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void setupProperties(NutMap conf) {
        if (expert instanceof Configurable)
            ((Configurable)expert).setupProperties(conf);
        if (executor instanceof Configurable)
            ((Configurable)executor).setupProperties(conf);
        if (runner instanceof Configurable)
            ((Configurable)runner).setupProperties(conf);
    }
}
