package org.nutz.trans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.nutz.lang.ComboException;

/**
 * 默认的事务实现上下文类,用户通常�?会直接使用到这个类. 这个类会关�?��?�一事务内多个数�?��?的连接
 * @author wendal(wendal1985@gmail.com)
 *
 */
public class NutTransaction extends Transaction {

    private static AtomicLong TransIdMaker = new AtomicLong();

    private List<ConnInfo> list;
    
    private long id;

    private static class ConnInfo {
        ConnInfo(DataSource ds, Connection conn, int level, boolean restoreAutoCommit) throws SQLException {
            this.ds = ds;
            this.conn = conn;
            this.oldLevel = conn.getTransactionIsolation();
            if (this.oldLevel != level) {
                conn.setTransactionIsolation(level);
                this.restoreIsoLevel = true;
            }
            this.restoreAutoCommit = restoreAutoCommit;
        }

        DataSource ds;
        Connection conn;
        int oldLevel;
        boolean restoreIsoLevel;
        boolean restoreAutoCommit;
    }

    /**
     * 新建上下文并�?始化自身的层次数�?�
     */
    public NutTransaction() {
        list = new ArrayList<ConnInfo>();
        id = TransIdMaker.getAndIncrement();
    }

    /**
     * �??交事务
     */
    protected void commit() {
        ComboException ce = new ComboException();
        for (ConnInfo cInfo : list) {
            try {
                // �??交事务
                cInfo.conn.commit();
                // �?��?旧的事务级别
                if (cInfo.conn.getTransactionIsolation() != cInfo.oldLevel)
                    cInfo.conn.setTransactionIsolation(cInfo.oldLevel);
            }
            catch (SQLException e) {
                ce.add(e);
            }
        }
        // 如果有一个数�?��?�??交时�?�生异常，抛出
        if (null != ce.getCause()) {
            throw ce;
        }
    }

    /**
     * 从数�?��?获�?�连接
     */
    @Override
    public Connection getConnection(DataSource dataSource) throws SQLException {
        for (ConnInfo p : list)
            if (p.ds == dataSource)
                return p.conn;
        Connection conn = dataSource.getConnection();
        // System.out.printf("=> %s\n", conn.toString());
        boolean restoreAutoCommit = false;
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
            restoreAutoCommit = true;
        }
        // Store conn, it will set the trans level
        list.add(new ConnInfo(dataSource, conn, getLevel(), restoreAutoCommit));
        return conn;
    }

    /**
     * 层次id
     */
    public long getId() {
        return id;
    }

    /**
     * 关闭事务,清�?�现场
     */
    @Override
    public void close() {
        ComboException ce = new ComboException();
        for (ConnInfo cInfo : list) {
            try {
                // 试图�?��?旧的事务级别
                if (!cInfo.conn.isClosed()) {
                    if (cInfo.restoreIsoLevel)
                        cInfo.conn.setTransactionIsolation(cInfo.oldLevel);
                    if (cInfo.restoreAutoCommit)
                        cInfo.conn.setAutoCommit(true);
                }
            }
            catch (Throwable e) {}
            finally {
                try {
                    cInfo.conn.close();
                }
                catch (Exception e) {
                    ce.add(e);
                }
            }
        }
        // 清除数�?��?记录
        list.clear();
    }

    /**
     * 执行回滚�?作
     */
    @Override
    protected void rollback() {
        for (ConnInfo cInfo : list) {
            try {
                cInfo.conn.rollback();
            }
            catch (Throwable e) {}
        }
    }

}
