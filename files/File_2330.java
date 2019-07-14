package com.zheng.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * åŠ¨æ€?æ•°æ?®æº?ï¼ˆæ•°æ?®æº?åˆ‡æ?¢ï¼‰
 * Created by ZhangShuzheng on 2017/1/15.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = getDataSource();
        LOGGER.info("å½“å‰?æ“?ä½œä½¿ç”¨çš„æ•°æ?®æº?ï¼š{}", dataSource);
        return dataSource;
    }

    /**
     * è®¾ç½®æ•°æ?®æº?
     *
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * èŽ·å?–æ•°æ?®æº?
     *
     * @return
     */
    public static String getDataSource() {
        String dataSource = CONTEXT_HOLDER.get();
        // å¦‚æžœæ²¡æœ‰æŒ‡å®šæ•°æ?®æº?ï¼Œä½¿ç”¨é»˜è®¤æ•°æ?®æº?
        if (null == dataSource) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getDefault());
        }
        return CONTEXT_HOLDER.get();
    }

    /**
     * æ¸…é™¤æ•°æ?®æº?
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
