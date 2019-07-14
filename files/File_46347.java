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
package com.alipay.sofa.rpc.tracer.sofatracer.factory;

import com.alipay.common.tracer.core.appender.encoder.SpanEncoder;
import com.alipay.common.tracer.core.reporter.facade.AbstractReporter;
import com.alipay.common.tracer.core.reporter.stat.SofaTracerStatisticReporter;
import com.alipay.common.tracer.core.reporter.stat.model.StatKey;
import com.alipay.common.tracer.core.reporter.stat.model.StatValues;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.alipay.sofa.rpc.common.annotation.JustForTest;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.tracer.sofatracer.log.digest.RpcClientDigestSpanJsonEncoder;
import com.alipay.sofa.rpc.tracer.sofatracer.log.digest.RpcServerDigestSpanJsonEncoder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * don't use for production
 *
 * @author <a href=mailto:leizhiyuan@gmail.com>leizhiyuan</a>
 */
@JustForTest
public class MemoryReporterImpl extends AbstractReporter {

    /**
     * Logger for this class
     */
    private static final Logger         LOGGER              = LoggerFactory.getLogger(MemoryReporterImpl.class);

    private SofaTracerStatisticReporter statReporter;

    private Map<StatKey, StatValues>    storeDatas          = new HashMap<StatKey, StatValues>();

    private SpanEncoder                 clientDigestEncoder = new RpcClientDigestSpanJsonEncoder();

    private SpanEncoder                 serverDigestEncoder = new RpcServerDigestSpanJsonEncoder();

    //客户端和�?务端一起本地进行测试的时候,是两个当�?类对象,所以这里用�?��?方法
    private static List<String>         clientDigestHolder  = new ArrayList<String>();
    private static List<String>         serverDigestHolder  = new ArrayList<String>();

    private static Lock                 lock                = new ReentrantLock();

    public MemoryReporterImpl(String digestLog, String digestRollingPolicy, String digestLogReserveConfig,
                              SpanEncoder<SofaTracerSpan> spanEncoder, SofaTracerStatisticReporter statReporter) {
        this.statReporter = statReporter;
    }

    @Override
    public void doReport(SofaTracerSpan span) {

        lock.lock();
        if (span.isClient()) {
            try {
                String result = clientDigestEncoder.encode(span);
                clientDigestHolder.add(result);
            } catch (IOException e) {
                LOGGER.error("encode error", e);
            }
        } else {
            try {
                String result = serverDigestEncoder.encode(span);
                serverDigestHolder.add(result);

            } catch (IOException e) {
                LOGGER.error("encode error", e);
            }
        }

        if (statReporter != null) {
            statisticReport(span);
        }

        lock.unlock();
    }

    @Override
    public String getReporterType() {
        return "Memory";
    }

    private void statisticReport(SofaTracerSpan span) {

        statReporter.reportStat(span);
        Field statDatas;
        Map<StatKey, StatValues> datas = null;
        try {
            statDatas = getDeclaredField(statReporter, "statDatas");
            statDatas.setAccessible(true);
            datas = (Map<StatKey, StatValues>) statDatas.get(statReporter);
        } catch (IllegalAccessException e) {
            LOGGER.error("statisticReport error", e);
        }

        storeDatas.putAll(datas);

    }

    public List<String> getClientDigestHolder() {
        return clientDigestHolder;
    }

    public List<String> getServerDigestHolder() {
        return serverDigestHolder;
    }

    public Map<StatKey, StatValues> getStoreDatas() {
        return storeDatas;
    }

    /**
     * 循环�?�上转型, 获�?�对象的 DeclaredField
     *
     * @param object    : �?类对象
     * @param fieldName : 父类中的属性�??
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里甚么都�?�?�?��?并且这里的异常必须这样写，�?能抛出去。
                //如果这里的异常打�?�或者往外抛，则就�?会执行clazz = clazz.getSuperclass(),最�?�就�?会进入到父类中了
            }
        }
        return null;
    }

    public boolean clearAll() {
        clientDigestHolder.clear();
        serverDigestHolder.clear();
        return true;
    }
}
