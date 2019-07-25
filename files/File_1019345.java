package cn.iocoder.mall.pay.biz.job;

import cn.iocoder.mall.pay.biz.dao.PayNotifyTaskMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayNotifyTaskDO;
import cn.iocoder.mall.pay.biz.service.PayNotifyServiceImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 支付通知�?试 Job
 */
@Component
@JobHandler(value = "payTransactionNotifyJob")
public class PayNotifyJob extends IJobHandler {

    @Autowired
    private PayNotifyTaskMapper payTransactionNotifyTaskMapper;

    @Autowired
    private PayNotifyServiceImpl payNotifyService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public ReturnT<String> execute(String param) {
        // 获得需�?通知的任务
        List<PayNotifyTaskDO> notifyTasks = payTransactionNotifyTaskMapper.selectByNotify();
        // 循环任务，�?��?通知
        for (PayNotifyTaskDO notifyTask : notifyTasks) {
            // �?��? MQ
            payNotifyService.sendNotifyMessage(notifyTask);
            // 更新最�?�通知时间
            // 1. 这样�?作，虽然�?�能会出现 MQ 消费快于下�?� PayTransactionNotifyTaskDO 的更新语�?�。但是，因为更新字段�?�?�，所以�?会有问题。
            // 2. �?�个视角，如果先更新 PayTransactionNotifyTaskDO ，�?�?��? MQ 消�?�。如果 MQ 消�?��?��?失败，则 PayTransactionNotifyTaskDO �?也�?会被轮询到了。
            // 3. 当然，最最最完美的�?，就是�?�事务消�?�，�?过这样�?�过于�?�?�~
            PayNotifyTaskDO updateNotifyTask = new PayNotifyTaskDO()
                    .setId(notifyTask.getId()).setLastExecuteTime(new Date());
            payTransactionNotifyTaskMapper.update(updateNotifyTask);
        }
        return new ReturnT<>("执行通知数：" + notifyTasks.size());
    }

}
