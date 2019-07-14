package com.sohu.cache.alert.strategy;

import java.util.Arrays;
import java.util.List;

import com.sohu.cache.alert.bean.AlertConfigBaseData;
import com.sohu.cache.entity.InstanceAlertConfig;
import com.sohu.cache.entity.InstanceAlertValueResult;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.redis.enums.RedisInfoEnum;

/**
 * RDB最近一次bgsave的执行状�?
 * 
 * @author leifu
 * @Date 2017年6月16日
 * @Time 下�?�2:34:10
 */
public class RdbLastBgsaveStatusAlertStrategy extends AlertConfigStrategy {
    @Override
    public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig, AlertConfigBaseData alertConfigBaseData) {
        Object object = getValueFromRedisInfo(alertConfigBaseData.getStandardStats(), RedisInfoEnum.rdb_last_bgsave_status.getValue());
        if (object == null) {
            return null;
        }
        // 关系比对
        String rdbLastBgsaveStatus = object.toString();
        boolean compareRight = isCompareStringRight(instanceAlertConfig, rdbLastBgsaveStatus);
        if (compareRight) {
            return null;
        }
        InstanceInfo instanceInfo = alertConfigBaseData.getInstanceInfo();
        return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig, instanceInfo, String.valueOf(rdbLastBgsaveStatus),
                instanceInfo.getAppId(), EMPTY));
    }

}
