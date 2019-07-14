package com.sohu.cache.alert.strategy;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

import com.sohu.cache.alert.bean.AlertConfigBaseData;
import com.sohu.cache.entity.InstanceAlertConfig;
import com.sohu.cache.entity.InstanceAlertValueResult;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.redis.enums.RedisInfoEnum;

/**
 * 分钟输入网络�?�?
 * @author leifu
 * @Date 2017年6月16日
 * @Time 下�?�2:34:10
 */
public class MinuteTotalNetInputMBytesAlertStrategy extends AlertConfigStrategy {
    @Override
    public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig, AlertConfigBaseData alertConfigBaseData) {
        Object totalNetInputBytesObject = getValueFromDiffInfo(alertConfigBaseData.getStandardStats(), RedisInfoEnum.total_net_input_bytes.getValue());
        if (totalNetInputBytesObject == null) {
            return null;
        }
        // 关系比对
        long totalNetInputBytes = NumberUtils.toLong(totalNetInputBytesObject.toString()) ;
        totalNetInputBytes = changeByteToMB(totalNetInputBytes);
        boolean compareRight = isCompareLongRight(instanceAlertConfig, totalNetInputBytes);
        if (compareRight) {
            return null;
        }
        InstanceInfo instanceInfo = alertConfigBaseData.getInstanceInfo();
        return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig, instanceInfo, String.valueOf(totalNetInputBytes),
                instanceInfo.getAppId(), MB_STRING));
    }

}
