package com.sohu.cache.alert.strategy;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.sohu.cache.alert.bean.AlertConfigBaseData;
import com.sohu.cache.entity.InstanceAlertConfig;
import com.sohu.cache.entity.InstanceAlertValueResult;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.redis.enums.RedisClusterInfoEnum;

/**
 * 集群�?功分�?槽个数监控
 * @author leifu
 * @Date 2017年6月21日
 * @Time 下�?�3:01:21
 */
public class ClusterSlotsOkAlertStrategy extends AlertConfigStrategy {

    @Override
    public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig, AlertConfigBaseData alertConfigBaseData) {
        Object object = getValueFromClusterInfo(alertConfigBaseData.getStandardStats(), RedisClusterInfoEnum.cluster_slots_ok.getValue());
        if (object == null) {
            return null;
        }
        // 关系比对
        int clusterSlotsOk = NumberUtils.toInt(object.toString());
        boolean compareRight = isCompareIntRight(instanceAlertConfig, clusterSlotsOk);
        if (compareRight) {
            return null;
        }
        InstanceInfo instanceInfo = alertConfigBaseData.getInstanceInfo();
        return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig, instanceInfo, String.valueOf(clusterSlotsOk),
                instanceInfo.getAppId(), EMPTY));
    }

}
