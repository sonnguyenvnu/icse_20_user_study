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
 * 客户端输入缓冲区最大buffer
 * 
 * @author leifu
 * @Date 2017年6月16日
 * @Time 下�?�2:34:10
 */
public class ClientBiggestInputBufAlertStrategy extends AlertConfigStrategy {
    @Override
    public List<InstanceAlertValueResult> checkConfig(InstanceAlertConfig instanceAlertConfig, AlertConfigBaseData alertConfigBaseData) {
        Object object = getValueFromRedisInfo(alertConfigBaseData.getStandardStats(), RedisInfoEnum.client_biggest_input_buf.getValue());
        if (object == null) {
            return null;
        }
        // 关系比对
        long clientBiggestInputBuf = NumberUtils.toLong(object.toString()) ;
        clientBiggestInputBuf = changeByteToMB(clientBiggestInputBuf);
        boolean compareRight = isCompareLongRight(instanceAlertConfig, clientBiggestInputBuf);
        if (compareRight) {
            return null;
        }
        InstanceInfo instanceInfo = alertConfigBaseData.getInstanceInfo();
        return Arrays.asList(new InstanceAlertValueResult(instanceAlertConfig, instanceInfo, String.valueOf(clientBiggestInputBuf),
                instanceInfo.getAppId(), MB_STRING));
    }

}
