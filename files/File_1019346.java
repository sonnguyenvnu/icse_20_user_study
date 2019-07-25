package cn.iocoder.mall.pay.biz.mq;

import cn.iocoder.mall.pay.api.message.PayTransactionSuccessMessage;
import cn.iocoder.mall.pay.biz.component.DubboReferencePool;
import cn.iocoder.mall.pay.biz.dao.PayTransactionMapper;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
@RocketMQMessageListener(
        topic = PayTransactionSuccessMessage.TOPIC,
        consumerGroup = "pay-consumer-group-" + PayTransactionSuccessMessage.TOPIC
)
public class PayTransactionSuccessConsumer extends AbstractPayNotifySuccessConsumer<PayTransactionSuccessMessage>
        implements RocketMQListener<PayTransactionSuccessMessage> {

    @Autowired
    private PayTransactionMapper payTransactionMapper;

    @Override
    protected String invoke(PayTransactionSuccessMessage message, DubboReferencePool.ReferenceMeta referenceMeta) {
        // 查询支付交易
        PayTransactionDO transaction = payTransactionMapper.selectById(message.getTransactionId());
        Assert.notNull(transaction, String.format("回调消�?�(%s) 订�?�交易�?能为空", message.toString()));
        // 执行调用
        GenericService genericService = referenceMeta.getService();
        String methodName = referenceMeta.getMethodName();
        return (String) genericService.$invoke(methodName, new String[]{String.class.getName(), Integer.class.getName()},
                new Object[]{message.getOrderId(), transaction.getPrice()});
    }

    @Override
    protected void afterInvokeSuccess(PayTransactionSuccessMessage message) {
        PayTransactionDO updateTransaction = new PayTransactionDO().setId(message.getTransactionId()).setFinishTime(new Date());
        payTransactionMapper.update(updateTransaction, null);
    }

}
