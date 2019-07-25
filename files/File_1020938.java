package net.csdn.modules.controller;

import com.google.inject.Inject;
import net.csdn.annotation.FDesc;
import net.csdn.annotation.MDesc;
import net.csdn.annotation.Param;
import net.csdn.annotation.rest.At;
import net.csdn.annotation.rest.BasicInfo;
import net.csdn.common.collect.Tuple3;
import net.csdn.common.logging.CSLogger;
import net.csdn.common.logging.Loggers;
import net.csdn.common.settings.Settings;
import org.joda.time.DateTime;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 4/9/14 WilliamZhu(allwefantasy@gmail.com)
 */

public class API {

    class AvgTime {
        AtomicLong timeStart;
        AtomicLong nowTimeRange;
        AtomicLong lastTimeRange;
        AtomicLong count;

        AvgTime(AtomicLong timeStart, AtomicLong nowTimeRange, AtomicLong lastTimeRange, AtomicLong count) {
            this.timeStart = timeStart;
            this.nowTimeRange = nowTimeRange;
            this.lastTimeRange = lastTimeRange;
            this.count = count;
        }
    }

    private Settings settings;
    private CSLogger logger = Loggers.getLogger(API.class);
    /*
      在特定internal 时间内的QPS
     */
    private ConcurrentHashMap<Method, Tuple3<AtomicLong/*时间戳*/, AtomicLong/*当�?interval时间内正在累积的访问数*/, AtomicLong/*上一个interval计算出的结果QPS*/>> APIQPS = new ConcurrentHashMap<Method, Tuple3<AtomicLong, AtomicLong, AtomicLong>>();
    private ConcurrentHashMap<Method, AvgTime> APIAVGTIME = new ConcurrentHashMap<Method, AvgTime>();
    /*
      �?个API 从系统�?�动开始，�?�个http状�?�?数目
     */
    private ConcurrentHashMap<Method, ConcurrentHashMap<Integer, AtomicLong>> APISTATUS = new ConcurrentHashMap<Method, ConcurrentHashMap<Integer, AtomicLong>>();


    private Long SystemStartTime = 0l;
    private boolean forceAPICheck = false;


    private int internal = 1000;
    private int averageTimeInternal = 1000;

    @Inject
    public API(Settings settings) {
        this.settings = settings;
        this.internal = settings.getAsInt("application.api.qps.internal", 1000);
        this.averageTimeInternal = settings.getAsInt("application.api.qps.average-time-internal", 1000);
        this.forceAPICheck = settings.getAsBoolean("application.api.strict.check", false);
        this.SystemStartTime = System.currentTimeMillis();
    }

    /*
     controller�?始化时需�?调用该方法
     */
    public void addPath(Method api) {
        APIQPS.putIfAbsent(api, new Tuple3<AtomicLong, AtomicLong, AtomicLong>(new AtomicLong(SystemStartTime), new AtomicLong(), new AtomicLong()));
        APIAVGTIME.putIfAbsent(api, new AvgTime(new AtomicLong(SystemStartTime), new AtomicLong(), new AtomicLong(), new AtomicLong()));
        APISTATUS.putIfAbsent(api, new ConcurrentHashMap<Integer, AtomicLong>());
    }

    /*
      校验API是�?�填写�?��?说明
     */
    public boolean validateAPI() {
        if (forceAPICheck) {
            return true;
        }
        return true;
    }


    /*
     收集�?个API的详细信�?�
     */
    public Map<Method, APIDesc> collectAPIInfoes() {
        Map<Method, APIDesc> APIDescs = new HashMap<Method, APIDesc>();
        for (Method method : APIQPS.keySet()) {
            At path = method.getAnnotation(At.class);
            MDesc mDesc = method.getAnnotation(MDesc.class);
            BasicInfo basicInfo = method.getAnnotation(BasicInfo.class);

            APIDesc apiDesc = new APIDesc();
            apiDesc.path = path.path()[0];
            apiDesc.desc = mDesc != null ? mDesc.value() : "";
            apiDesc.qps = APIQPS.get(method).v3().get();
            apiDesc.avgTime = APIAVGTIME.get(method).lastTimeRange.get();
            apiDesc.paramDesces = createParamDescs(method);
            List<ResponseStatus> responseStatuses = new ArrayList<ResponseStatus>();
            for (Map.Entry<Integer, AtomicLong> item : APISTATUS.get(method).entrySet()) {
                ResponseStatus responseStatus = new ResponseStatus();
                responseStatus.status = item.getKey();
                responseStatus.count = item.getValue().get();
                responseStatuses.add(responseStatus);
            }
            apiDesc.responseStatuses = responseStatuses;
            APIDescs.put(method, apiDesc);
        }
        return APIDescs;
    }

    private List<ParamDesc> createParamDescs(Method method) {
        List<ParamDesc> paramDescs = new ArrayList<ParamDesc>();
        Annotation[][] paramAnnoes = method.getParameterAnnotations();
        Class[] types = method.getParameterTypes();
        for (int i = 0; i < paramAnnoes.length; i++) {
            Annotation[] paramAnno = paramAnnoes[i];
            if (paramAnno.length == 0) continue;
            ParamDesc paramDesc = new ParamDesc();
            for (Annotation item : paramAnno) {
                if (item instanceof FDesc) {
                    paramDesc.desc = ((FDesc) item).value();
                }
                if (item instanceof Param) {
                    paramDesc.name = ((Param) item).value();
                }
            }
            paramDesc.ptype = types[i].getName();
            paramDescs.add(paramDesc);
        }
        return paramDescs;
    }

    public boolean enable() {
        return (settings.getAsBoolean("application.api.qps.enable", false));
    }

    /*
      QPS 统计
     */
    public synchronized void qpsIncrement(Method api) {
        if (!enable() || api == null) return;
        long now = System.currentTimeMillis();
        Tuple3<AtomicLong, AtomicLong, AtomicLong> info = APIQPS.get(api);
        if (now - info.v1().get() > internal) {
            info.v3().set(info.v2().get());
            info.v2().set(0);
            info.v1().set(now);
        } else {
            info.v2().incrementAndGet();
        }

    }

    public synchronized void averageTimeIncrement(Method api, long time) {
        if (!enable() || api == null) return;
        long now = System.currentTimeMillis();
        AvgTime info = APIAVGTIME.get(api);
        info.count.incrementAndGet();
        if (now - info.timeStart.get() > averageTimeInternal) {
            if (info.count.get() == 0) {
                info.lastTimeRange.set(0);
            } else {
                info.lastTimeRange.set(info.nowTimeRange.get() / info.count.get());
            }
            info.nowTimeRange.set(0);
            info.timeStart.set(now);
        } else {
            info.nowTimeRange.addAndGet(time);
        }

    }

    /*
      状�?�?统计
     */
    public synchronized void statusIncrement(Method api, int status) {
        if (!enable() || api == null) return;
        ConcurrentHashMap<Integer, AtomicLong> chm = APISTATUS.get(api);
        if (!chm.containsKey(status)) {
            chm.put(status, new AtomicLong());
        }
        chm.get(status).incrementAndGet();
    }

    public String systemStartTime() {
        return new DateTime(SystemStartTime).toString("yyyy-MM-dd HH-mm-ss S");
    }

}
