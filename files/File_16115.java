package org.hswebframework.web.datasource.switcher;

import org.hswebframework.web.ThreadLocalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 默认的动�?数�?�库切�?�器,基于ThreadLocal,queue
 *
 * @author zhouhao
 * @since 3.0.8
 */
public class DefaultDatabaseSwitcher implements DatabaseSwitcher {

    //默认数�?��?标识
    private static final String DEFAULT_DATASOURCE_ID = DatabaseSwitcher.class.getName() + "_default_";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Deque<String> getUsedHistoryQueue() {
        // 从ThreadLocal中获�?�一个使用记录
        return ThreadLocalUtils.get(DefaultDatabaseSwitcher.class.getName() + "_queue", LinkedList::new);
    }

    @Override
    public void useLast() {
        // 没有上一次了
        if (getUsedHistoryQueue().isEmpty()) {
            return;
        }
        //移除队尾,则当�?的队尾则为上一次的数�?��?
        getUsedHistoryQueue().removeLast();
        if (logger.isDebugEnabled()) {
            String current = currentDatabase();
            if (null != current) {
                logger.debug("try use database : {}", currentDatabase());
            } else {
                logger.debug("try use last default database");
            }
        }
    }

    @Override
    public void use(String dataSourceId) {
        //添加对队尾
        getUsedHistoryQueue().addLast(dataSourceId);
        if (logger.isDebugEnabled()) {
            logger.debug("try use database : {}", dataSourceId);
        }
    }

    @Override
    public void useDefault() {
        getUsedHistoryQueue().addLast(DEFAULT_DATASOURCE_ID);
        if (logger.isDebugEnabled()) {
            logger.debug("try use default database");
        }
    }

    @Override
    public String currentDatabase() {
        if (getUsedHistoryQueue().isEmpty()) {
            return null;
        }

        String activeId = getUsedHistoryQueue().getLast();
        if (DEFAULT_DATASOURCE_ID.equals(activeId)) {
            return null;
        }
        return activeId;
    }

    @Override
    public void reset() {
        getUsedHistoryQueue().clear();
        if (logger.isDebugEnabled()) {
            logger.debug("reset database used history");
        }
    }
}
