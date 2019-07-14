/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.client.aft.impl;

import com.alipay.sofa.rpc.client.aft.FaultToleranceConfigManager;
import com.alipay.sofa.rpc.client.aft.InvocationStat;
import com.alipay.sofa.rpc.client.aft.InvocationStatDimension;
import com.alipay.sofa.rpc.client.aft.InvocationStatFactory;
import com.alipay.sofa.rpc.client.aft.MeasureModel;
import com.alipay.sofa.rpc.client.aft.MeasureResult;
import com.alipay.sofa.rpc.client.aft.MeasureResultDetail;
import com.alipay.sofa.rpc.client.aft.MeasureState;
import com.alipay.sofa.rpc.client.aft.MeasureStrategy;
import com.alipay.sofa.rpc.client.aft.ProviderInfoWeightManager;
import com.alipay.sofa.rpc.common.utils.CalculateUtils;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * �?务水平ip资�?度�?策略 如果�?个ip的异常率大于该�?务所有ip的平�?�异常率到一定比例，则判定为异常。
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
@Extension("serviceHorizontal")
public class ServiceHorizontalMeasureStrategy implements MeasureStrategy {

    /**
     * Logger for this class
     */
    private static final Logger LOGGER                   = LoggerFactory
                                                             .getLogger(ServiceHorizontalMeasureStrategy.class);

    /**
     * 如果�?置的�?�与统计的窗�?�内最低调用次数�?能�?于1,�?制
     */
    private static final long   LEGAL_LEAST_WINDOW_COUNT = 1L;

    @Override
    public MeasureResult measure(MeasureModel measureModel) {

        MeasureResult measureResult = new MeasureResult();
        measureResult.setMeasureModel(measureModel);

        String appName = measureModel.getAppName();
        List<InvocationStat> stats = measureModel.getInvocationStats();
        if (!CommonUtils.isNotEmpty(stats)) {
            return measureResult;
        }

        //如果有被新剔除的InvocationStat，则�?会存在于该次获�?�结果中。
        List<InvocationStat> invocationStats = getInvocationStatSnapshots(stats);

        long timeWindow = FaultToleranceConfigManager.getTimeWindow(appName);
        /* leastWindowCount在�?�一次度�?中�?�?�?�?�*/
        long leastWindowCount = FaultToleranceConfigManager.getLeastWindowCount(appName);
        leastWindowCount = leastWindowCount < LEGAL_LEAST_WINDOW_COUNT ? LEGAL_LEAST_WINDOW_COUNT
            : leastWindowCount;

        /* 计算平�?�异常率和度�?�?�个ip的时候都需�?使用到appWeight*/
        double averageExceptionRate = calculateAverageExceptionRate(invocationStats, leastWindowCount);

        double leastWindowExceptionRateMultiple = FaultToleranceConfigManager
            .getLeastWindowExceptionRateMultiple(appName);

        for (InvocationStat invocationStat : invocationStats) {
            MeasureResultDetail measureResultDetail = null;
            InvocationStatDimension statDimension = invocationStat.getDimension();

            long windowCount = invocationStat.getInvokeCount();
            long invocationLeastWindowCount = getInvocationLeastWindowCount(invocationStat,
                ProviderInfoWeightManager.getWeight(statDimension.getProviderInfo()),
                leastWindowCount);
            if (averageExceptionRate == -1) {
                measureResultDetail = new MeasureResultDetail(statDimension, MeasureState.IGNORE);
            } else {
                if (invocationLeastWindowCount != -1 && windowCount >= invocationLeastWindowCount) {
                    double windowExceptionRate = invocationStat.getExceptionRate();
                    if (averageExceptionRate == 0) {
                        measureResultDetail = new MeasureResultDetail(statDimension, MeasureState.HEALTH);
                    } else {
                        double windowExceptionRateMultiple = CalculateUtils.divide(
                            windowExceptionRate, averageExceptionRate);
                        measureResultDetail = windowExceptionRateMultiple >= leastWindowExceptionRateMultiple ?
                            new MeasureResultDetail(statDimension, MeasureState.ABNORMAL) :
                            new MeasureResultDetail(statDimension, MeasureState.HEALTH);
                    }
                    measureResultDetail.setAbnormalRate(windowExceptionRate);
                    measureResultDetail.setAverageAbnormalRate(averageExceptionRate);
                    measureResultDetail.setLeastAbnormalRateMultiple(leastWindowExceptionRateMultiple);
                } else {
                    measureResultDetail = new MeasureResultDetail(statDimension, MeasureState.IGNORE);
                }
            }

            measureResultDetail.setWindowCount(windowCount);
            measureResultDetail.setTimeWindow(timeWindow);
            measureResultDetail.setLeastWindowCount(invocationLeastWindowCount);
            measureResult.addMeasureDetail(measureResultDetail);
        }

        logMeasureResult(measureResult, timeWindow, leastWindowCount, averageExceptionRate,
            leastWindowExceptionRateMultiple);

        InvocationStatFactory.updateInvocationStats(invocationStats);
        return measureResult;
    }

    /**
     * Print the measurement result details for each time window.
     * @param measureResult
     * @param timeWindow
     * @param leastWindowCount
     * @param averageExceptionRate
     * @param leastWindowExceptionRateMultiple
     */
    private void logMeasureResult(MeasureResult measureResult, long timeWindow, long leastWindowCount,
                                  double averageExceptionRate,
                                  double leastWindowExceptionRateMultiple) {
        if (measureResult == null) {
            return;
        }

        MeasureModel measureModel = measureResult.getMeasureModel();
        String appName = measureModel.getAppName();
        if (!LOGGER.isDebugEnabled(appName)) {
            return;
        }

        String service = measureModel.getService();
        List<InvocationStat> stats = measureModel.getInvocationStats();
        List<MeasureResultDetail> details = measureResult.getAllMeasureResultDetails();

        StringBuilder info = new StringBuilder();

        info.append("measure info: service[" + service + "];stats{");
        for (InvocationStat stat : stats) {
            info.append(stat.getDimension().getIp());
            info.append(",");
        }
        if (stats.size() > 0) {
            info.deleteCharAt(info.length() - 1);
        }
        info.append("};details{");

        info.append("timeWindow[" + timeWindow + "];leastWindowCount[" + leastWindowCount + "];averageExceptionRate[" +
            averageExceptionRate
            + "];leastWindowExceptionRateMultiple[" + leastWindowExceptionRateMultiple + "];");
        info.append("detail[");
        for (MeasureResultDetail detail : details) {

            String ip = detail.getInvocationStatDimension().getIp();
            double abnormalRate = detail.getAbnormalRate();
            long invocationLeastWindowCount = detail.getLeastWindowCount();
            String measureState = detail.getMeasureState().name();

            info.append("(ip:" + ip + ",abnormalRate:" + abnormalRate + ",invocationLeastWindowCount:" +
                invocationLeastWindowCount
                + ",measureState:" + measureState + ")");
        }
        info.append("]");

        LOGGER.debugWithApp(appName, info.toString());
    }

    /**
     * 对批�?Invocation对应的InvocationStat进行一个快照
     *
     * @param stats Dimensions of invocation statistics
     * @return List<InvocationStat>
     */
    public static List<InvocationStat> getInvocationStatSnapshots(List<InvocationStat> stats) {
        List<InvocationStat> snapshots = new ArrayList<InvocationStat>(stats.size());
        for (InvocationStat stat : stats) {
            InvocationStat snapshot = stat.snapshot();
            if (snapshot.getInvokeCount() <= 0) {
                if (stat.getUselessCycle().incrementAndGet() > 6) {
                    // 6 个时间窗�?�无调用，删除统计
                    InvocationStatFactory.removeInvocationStat(stat);
                    InvocationStatDimension dimension = stat.getDimension();
                    String appName = dimension.getAppName();
                    if (LOGGER.isDebugEnabled(appName)) {
                        LOGGER.debugWithApp(appName, "Remove invocation stat : {}, {} because of useless cycle > 6",
                            dimension.getDimensionKey(), dimension.getProviderInfo());
                    }
                }
            } else {
                stat.getUselessCycle().set(0);
                snapshots.add(snapshot);
            }
        }
        return snapshots;
    }

    /**
     * All measure model
     */
    protected final ConcurrentMap<String, MeasureModel> appServiceMeasureModels = new ConcurrentHashMap<String, MeasureModel>();

    /**
     * 如果该Invocation�?属于一个MeasureModel，那么创建一个MeasureModel。并返回该MeasureModel。
     * 如果该Invocation属于一个MeasureModel，那么将该Invocation加入到该MeasureModel中。返回null。
     *
     * @param invocationStat InvocationStat
     * @return MeasureModel
     */
    @Override
    public MeasureModel buildMeasureModel(InvocationStat invocationStat) {
        InvocationStatDimension statDimension = invocationStat.getDimension();
        String key = statDimension.getDimensionKey();
        MeasureModel measureModel = appServiceMeasureModels.get(key);
        if (measureModel == null) {
            measureModel = new MeasureModel(statDimension.getAppName(), statDimension.getService());
            MeasureModel oldMeasureModel = appServiceMeasureModels.putIfAbsent(key, measureModel);
            if (oldMeasureModel == null) {
                measureModel.addInvocationStat(invocationStat);
                return measureModel;
            } else {
                oldMeasureModel.addInvocationStat(invocationStat);
                return null;
            }
        } else {
            measureModel.addInvocationStat(invocationStat);
            return null;
        }
    }

    @Override
    public MeasureModel removeMeasureModel(InvocationStat invocationStat) {
        InvocationStatDimension statDimension = invocationStat.getDimension();
        MeasureModel measureModel = appServiceMeasureModels.get(statDimension.getDimensionKey());
        if (measureModel != null) {
            measureModel.removeInvocationStat(invocationStat);
        }
        return measureModel;
    }

    /**
     * 计算平�?�异常率，如果调用次数�?于leastWindowCount则�?�?�与计算。 如果所有调用次数�?�为0则返回-1
     *
     * @param invocationStats List<InvocationStat>
     * @param leastWindowCount leastWindowCount
     * @return The average exception rate of all invocation statics
     */
    private double calculateAverageExceptionRate(List<InvocationStat> invocationStats, long leastWindowCount) {
        long sumException = 0;
        long sumCall = 0;
        for (InvocationStat invocationStat : invocationStats) {

            long invocationLeastWindowCount = getInvocationLeastWindowCount(invocationStat,
                ProviderInfoWeightManager.getWeight(invocationStat.getDimension().getProviderInfo()),
                leastWindowCount);

            if (invocationLeastWindowCount != -1
                && invocationStat.getInvokeCount() >= invocationLeastWindowCount) {
                sumException += invocationStat.getExceptionCount();
                sumCall += invocationStat.getInvokeCount();
            }
        }
        if (sumCall == 0) {
            return -1;
        }
        return CalculateUtils.divide(sumException, sumCall);
    }

    /**
     * 根�?�Invocation的实际�?��?计算该Invocation的实际最�?窗�?�调用次数 如果目标地�?�原始�?��?为0，或者地�?�已�?被剔除则返回-1。
     *
     * @param invocationStat InvocationStat
     * @param weight weight
     * @param leastWindowCount original least Window count
     * @return leastWindowCount
     */
    private long getInvocationLeastWindowCount(InvocationStat invocationStat, Integer weight, long leastWindowCount) {
        InvocationStatDimension statDimension = invocationStat.getDimension();
        Integer originWeight = statDimension.getOriginWeight();
        if (originWeight == 0) {
            LOGGER.errorWithApp(statDimension.getAppName(), "originWeight is 0,but is invoked. service["
                + statDimension.getService() + "];ip["
                + statDimension.getIp() + "].");
            return -1;
        } else if (weight == null) { //如果地�?�还未被调控过或者已�?�?��?。
            return leastWindowCount;
        } else if (weight == -1) { //如果地�?�被剔除
            return -1;
        }

        double rate = CalculateUtils.divide(weight, originWeight);
        long invocationLeastWindowCount = CalculateUtils.multiply(leastWindowCount, rate);
        return invocationLeastWindowCount < LEGAL_LEAST_WINDOW_COUNT ? LEGAL_LEAST_WINDOW_COUNT
            : invocationLeastWindowCount;
    }

}
