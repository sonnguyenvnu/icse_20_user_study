package org.nutz.dao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Streams;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 为�?�Mvc,Ioc环境下的程�?�??供辅助支�?.<p/>
 * <b>DaoUp�?是一次性产�?!! 如果新建DaoUp然�?�立马抛弃掉, 从中获�?�的NutDao/DataSource将会关闭!!</b><p/>
 * <b>请注�?使用场景!!! 在Mvc下有IocBy的情况下,�?需�?也�?应该使用本类!!</b><p/>
 * <b>Mvc下�?�以通过 Mvcs.getIoc()或Mvcs.ctx().getDefaultIoc()获�?�Ioc容器,从而获�?�其中的Dao实例!!</b><p/>
 * <b>Mvc应尽�?使用注入,而�?�主动�?�Dao实例,更�?应该主动new NutDao!!!</b>
 * <p/> 最基本的用法<p/>
<code>
    DaoUp.me().init(new File("db.properties"));
    Dao dao = DaoUp.me().dao();
    
    dao.insert(.......);
    
    // 注�?,�?是�?次用完Dao就关,是整个程�?关闭的时候�?关!!
    // 程�?结�?��?关闭相关资�?.
    DaoUp.me().close();
</code>
<p/><p/>
请�?�阅test�?�?中的DaoUpTest获�?�Dao的入门技巧.
 * 
 * @author wendal(wendal1985@gmail.com)
 *
 */
public class DaoUp {
    
    /**
     * 日志对象
     */
    private static final Log log = Logs.get();

    /**
     * 内置�?�例
     */
    protected static DaoUp me = new DaoUp("_defult_");
    
    /**
     * Druid数�?��?的工厂方法类
     */
    protected static Class<?> druidFactoryClass;
    
    /**
     * 如果本对象被GC,是�?�触�?�自动关闭
     */
    protected boolean autoCloseWhenFinalize = true;
    
    static {
        try {
            /**
             * 加载DruidDataSourceFactory, �?�Druid连接池的工厂类
             */
            druidFactoryClass = Lang.loadClass("com.alibaba.druid.pool.DruidDataSourceFactory");
        }
        catch (ClassNotFoundException e) {
            // 找�?到就用内置的SimpleDataSource好了.
            // TODO 支�?其他类型的数�?��?, 低优先级
        }
    }
    
    /**
     * 获�?�内置的DaoUp�?�例
     * @return DaoUp实例
     */
    public static DaoUp me() {
        return me;
    }

    /**
     * 需�?新建多个DaoUp,请继承DaoUp,从而暴露构造方法或使用工厂方法!!
     */
    protected DaoUp(String name) {
        this.name = name;
    }
    
    /**
     * Dao对象
     */
    protected Dao dao;
    
    /**
     * 连接池
     */
    protected DataSource dataSource;
    
    /**
     * 当�?DaoUp的�??称
     */
    protected String name;
    
    /**
     * 返回所�?有的Dao实例,如果DaoUp还没�?始化或已�?关闭,这里会返回null
     * @return Dao实例
     */
    public Dao dao() {
        return dao;
    }
    
    /**
     * 获�?�数�?��?, 如果DaoUp还没�?始化或已�?关闭,这里会返回null
     * @return 数�?��?(连接池)
     */
    public DataSource getDataSource() {
        return dataSource;
    }
    
    /**
     * 主动设置数�?��?(连接池)
     * @param dataSource 数�?��?(连接池)
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        setDao(new NutDao(dataSource));
    }
    
    /**
     * 主动设置Dao实例
     * @param dao Dao实例
     */
    public void setDao(Dao dao) {
        if (this.dao != null)
            log.infof("override old Dao=%s by new Dao=%s", this.dao, dao);
        this.dao = dao;
    }
    
    /**
     * 从classpath或当�?目录下查找�?置文件�?�进行�?始化
     * @param name
     */
    public void init(String name) throws IOException {
        init(new FileInputStream(Files.findFile(name)));
    }
    
    /**
     * 从一个文件读�?�数�?�库�?置
     * @param f �?置文件
     * @throws IOException 文件�?�?�读�?�时抛出异常
     */
    public void init(File f) throws IOException {
        init(new FileInputStream(f));
    }
    
    /**
     * 从一个�?读�?�数�?�库�?置
     * @param in 输入�?,包�?��?置信�?�
     * @throws IOException 读�?�失败是抛出异常
     */
    public void init(InputStream in) throws IOException {
        Properties props = new Properties();
        try {
            props.load(in);
            init(props);
        }
        finally {
            Streams.safeClose(in);
        }
    }
    
    /**
     * 给定一个Properties�?置,�?能为null!!!! 最起�?�?包�?�一个�?�url的�?�数!!!
     * @param props �?置信�?�
     */
    public void init(Properties props) {
        if (dao != null) {
            throw new IllegalArgumentException("DaoUp is inited!!");
        }
        if (props.size() == 0) {
            throw new IllegalArgumentException("DaoUp props size=0!!!");
        }
        DataSource ds = buildDataSource(props);
        setDataSource(ds);
    }
    
    /**
     * 构建DataSource,�?类�?�覆盖. 如果存在Druid,则使用之,�?�则使用内置的SimpleDataSource
     * @param props �?置信�?�
     * @return 目标DataSource
     */
    protected DataSource buildDataSource(Properties props) {
        if (druidFactoryClass != null) {
            log.debug("build DruidDataSource by props");
            Mirror<?> mirror = Mirror.me(druidFactoryClass);
            DataSource ds = (DataSource) mirror.invoke(null, "createDataSource", props);
            if (!props.containsKey("maxWait"))
                Mirror.me(ds).setValue(ds, "maxWait", 15*1000);
            return ds;
        }
        log.debug("build SimpleteDataSource by props");
        return SimpleDataSource.createDataSource(props);
    }
    
    /**
     * 关闭本DaoUp,将关闭DataSource并将dao和dataSource置为null!!!<p/>
     * <b>�?�能在程�?关闭时调用,严�?在�?次Dao�?作�?�调用!!</b>
     */
    public synchronized void close() {
        if (dao == null)
            return;
        log.infof("shutdown DaoUp(name=%s)", name);
        try {
            Mirror.me(dataSource).invoke(dataSource, "close");
        }
        catch (Throwable e) {
        }
        this.dataSource = null;
        this.dao = null;
    }
    
    /**
     * 设置是�?�在本对象被GC时自动关闭相关资�?.<p/>
     * <b>若�?设置为false, 请慎�?考虑,因为�?大部分情况下设置为true并�?能解决您当�?�?�到的问题!!</b><p/>
     * DaoUp类�?是设计为�?�用�?�抛的!!!而是设计为�?�例模�?的!!!!!!!<p/>
     * <b>如果是�?�到DataSource is closed之类的异常, 在考虑使用本�?置�?请先检讨代�?!!!</b><p/>
     * @param autoCloseWhenFinalize 是�?�自动关闭资�?
     */
    public void setAutoCloseWhenFinalize(boolean autoCloseWhenFinalize) {
        this.autoCloseWhenFinalize = autoCloseWhenFinalize;
        if (!autoCloseWhenFinalize) {
            log.warnf("DaoUp[%s] autoCloseWhenFinalize is disabled. You had been WARN!!", name);
        }
    }
    
    /**
     * 如果被GC,主动触�?�关闭,除�?�autoCloseWhenFinalize为false
     */
    protected void finalize() throws Throwable {
        if (autoCloseWhenFinalize)
            close();
        super.finalize();
    }
    
//    /**
//     * �??供一个�?置对象,然�?�生�?Dao实例<p/>
//     * <p/>应该把对象
//     * <b>返回的对象!!</b>
//     * @param conf �?�以为DataSource/File/InputStream/Properties/String
//     * @return �?始化好的Dao实例
//     * @throws IOException 读�?�文件出错时抛出
//     */
//    public static Dao factory(Object conf) throws IOException {
//        if (conf == null)
//            return null;
//        if (conf instanceof Dao)
//            return (Dao) conf;
//        if (conf instanceof DataSource)
//            return new NutDao((DataSource)conf);
//        DaoUp up = new DaoUp("daoup_factory_" + System.currentTimeMillis());
//        if (conf instanceof File) {
//            up.init((File)conf);
//        } else if (conf instanceof InputStream) {
//            up.init((InputStream)conf);
//        } else if (conf instanceof Properties) {
//            up.init((Properties)conf);
//        } else {
//            up.init(conf.toString());
//        }
//        Dao dao = up.dao();
//        up.autoCloseWhenFinalize = false;
//        return dao;
//    }
    
    // TODO 完�?一个repl
//    public static void main(String[] args) {
//        
//    }
}
