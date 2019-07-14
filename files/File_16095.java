package org.hswebframework.web.datasource;

import org.hswebframework.web.datasource.exception.DataSourceNotFoundException;
import org.hswebframework.web.datasource.switcher.*;

/**
 * ç”¨äºŽæ“?ä½œåŠ¨æ€?æ•°æ?®æº?,å¦‚èŽ·å?–å½“å‰?ä½¿ç”¨çš„æ•°æ?®æº?,ä½¿ç”¨switcheråˆ‡æ?¢æ•°æ?®æº?ç­‰
 *
 * @author zhouhao
 * @since 3.0
 */
public final class DataSourceHolder {

    private static final DataSourceSwitcher defaultSwitcher = new DefaultDataSourceSwitcher();

    /**
     * åŠ¨æ€?æ•°æ?®æº?åˆ‡æ?¢å™¨
     */
    static volatile DataSourceSwitcher dataSourceSwitcher = defaultSwitcher;
    /**
     * åŠ¨æ€?æ•°æ?®æº?æœ?åŠ¡
     */
    static volatile DynamicDataSourceService dynamicDataSourceService;

    static volatile TableSwitcher tableSwitcher = new DefaultTableSwitcher();

    static volatile DatabaseSwitcher databaseSwitcher = new DefaultDatabaseSwitcher();


    public static void checkDynamicDataSourceReady() {
        if (dynamicDataSourceService == null) {
            throw new UnsupportedOperationException("dataSourceService not ready");
        }
    }

    /**
     * @return åŠ¨æ€?æ•°æ?®æº?åˆ‡æ?¢å™¨
     */
    public static DataSourceSwitcher switcher() {
        return dataSourceSwitcher;
    }

    /**
     * @return è¡¨åˆ‡æ?¢å™¨, ç”¨äºŽåŠ¨æ€?åˆ‡æ?¢ç³»ç»ŸåŠŸèƒ½è¡¨
     */
    public static TableSwitcher tableSwitcher() {
        return tableSwitcher;
    }

    /**
     * @return æ•°æ?®åº“åˆ‡æ?¢å™¨
     * @since 3.0.8
     */
    public static DatabaseSwitcher databaseSwitcher() {
        return databaseSwitcher;
    }


    /**
     * @return é»˜è®¤æ•°æ?®æº?
     */
    public static DynamicDataSource defaultDataSource() {
        checkDynamicDataSourceReady();
        return dynamicDataSourceService.getDefaultDataSource();
    }

    /**
     * æ ¹æ?®æŒ‡å®šçš„æ•°æ?®æº?idèŽ·å?–åŠ¨æ€?æ•°æ?®æº?
     *
     * @param dataSourceId æ•°æ?®æº?id
     * @return åŠ¨æ€?æ•°æ?®æº?
     * @throws DataSourceNotFoundException å¦‚æžœæ•°æ?®æº?ä¸?å­˜åœ¨å°†æŠ›å‡ºæ­¤å¼‚å¸¸
     */
    public static DynamicDataSource dataSource(String dataSourceId) {
        checkDynamicDataSourceReady();
        return dynamicDataSourceService.getDataSource(dataSourceId);
    }

    /**
     * @return å½“å‰?ä½¿ç”¨çš„æ•°æ?®æº?
     */
    public static DynamicDataSource currentDataSource() {
        String id = dataSourceSwitcher.currentDataSourceId();
        if (id == null) {
            return defaultDataSource();
        }
        checkDynamicDataSourceReady();
        return dynamicDataSourceService.getDataSource(id);
    }

    /**
     * @return å½“å‰?ä½¿ç”¨çš„æ•°æ?®æº?æ˜¯å?¦ä¸ºé»˜è®¤æ•°æ?®æº?
     */
    public static boolean currentIsDefault() {
        return dataSourceSwitcher.currentDataSourceId() == null;
    }

    /**
     * åˆ¤æ–­æŒ‡å®šidçš„æ•°æ?®æº?æ˜¯å?¦å­˜åœ¨
     *
     * @param id æ•°æ?®æº?id {@link DynamicDataSource#getId()}
     * @return æ•°æ?®æº?æ˜¯å?¦å­˜åœ¨
     */
    public static boolean existing(String id) {
        try {
            checkDynamicDataSourceReady();
            return dynamicDataSourceService.getDataSource(id) != null;
        } catch (DataSourceNotFoundException e) {
            return false;
        }
    }

    /**
     * @return å½“å‰?ä½¿ç”¨çš„æ•°æ?®æº?æ˜¯å?¦å­˜åœ¨
     */
    public static boolean currentExisting() {
        if (currentIsDefault()) {
            return true;
        }
        try {
            return currentDataSource() != null;
        } catch (DataSourceNotFoundException e) {
            return false;
        }
    }

    /**
     * @return å½“å‰?æ•°æ?®åº“ç±»åž‹
     */
    public static DatabaseType currentDatabaseType() {
        return currentDataSource().getType();
    }

    /**
     * @return é»˜è®¤çš„æ•°æ?®åº“ç±»åž‹
     */
    public static DatabaseType defaultDatabaseType() {
        return defaultDataSource().getType();
    }
}
