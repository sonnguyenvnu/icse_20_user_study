package com.sohu.cache.alert.strategy;

import java.util.Arrays;
import java.util.List;

import com.sohu.cache.alert.bean.AlertConfigBaseData;
import com.sohu.cache.entity.InstanceAlertConfig;
import com.sohu.cache.entity.InstanceAlertValueResult;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.redis.enums.RedisClusterInfoEnum;

/**
 * 集群状�?监控
 * @author leifu
 * @Date 2017年6月21日
 * @Time 下�?�3:01:21
 */
public class ClusterStateAlertStrategy extends AlertConfigStrategy {

    @Override
    public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig, AlertConfigBaseData alertConfigBaseData) {
        Object object = getValueFromClusterInfo(alertConfigBaseData.getStandardStats(), RedisClusterInfoEnum.cluster_state.getValue());
        if (object == null) {
            return null;
        }
        // 关系比对
        String clusterState = object.toString();
        boolean compareRight = isCompareStringRight(instanceAlertConfig, clusterState);
        if (compareRight) {
            return null;
        }
        InstanceInfo instanceInfo = alertConfigBaseData.getInstanceInfo();
        return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig, instanceInfo, String.valueOf(clusterState),
                instanceInfo.getAppId(), EMPTY));
    }

}
