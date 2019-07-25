package cn.iocoder.mall.order.biz.service;

import cn.iocoder.common.framework.constant.DeletedStatusEnum;
import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.admin.api.DataDictService;
import cn.iocoder.mall.admin.api.bo.datadict.DataDictBO;
import cn.iocoder.mall.order.api.OrderLogisticsService;
import cn.iocoder.mall.order.api.OrderReturnService;
import cn.iocoder.mall.order.api.bo.OrderLastLogisticsInfoBO;
import cn.iocoder.mall.order.api.bo.OrderReturnInfoBO;
import cn.iocoder.mall.order.api.bo.OrderReturnListBO;
import cn.iocoder.mall.order.api.constant.*;
import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnQueryDTO;
import cn.iocoder.mall.order.biz.convert.OrderReturnConvert;
import cn.iocoder.mall.order.biz.dao.OrderItemMapper;
import cn.iocoder.mall.order.biz.dao.OrderMapper;
import cn.iocoder.mall.order.biz.dao.OrderReturnMapper;
import cn.iocoder.mall.order.biz.dataobject.OrderDO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import cn.iocoder.mall.pay.api.PayRefundService;
import cn.iocoder.mall.pay.api.dto.refund.PayRefundSubmitDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * è®¢å?•é€€è´§ service
 *
 * @author Sin
 * @time 2019-03-30 15:35
 */
@Service
@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.OrderReturnService.version}")
public class OrderReturnServiceImpl implements OrderReturnService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderReturnMapper orderReturnMapper;

    @Reference(validation = "true")
    private OrderLogisticsService orderLogisticsService;
    @Reference(validation = "true", version = "${dubbo.consumer.PayRefundService.version}")
    private PayRefundService payRefundService;
    @Reference(validation = "true", version = "${dubbo.consumer.DataDictService.version}")
    private DataDictService dataDictService;


    @Override
    public CommonResult orderReturnApply(OrderReturnApplyDTO orderReturnDTO) {
        OrderDO checkOrder = orderMapper.selectById(orderReturnDTO.getOrderId());

        // æ£€æŸ¥è®¢å?•æ˜¯å?¦ å­˜åœ¨
        if (checkOrder == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        // è½¬æ?¢ DO
        OrderReturnDO orderReturnDO = OrderReturnConvert.INSTANCE.convert(orderReturnDTO);
        orderReturnDO
                .setOrderId(checkOrder.getId())
                // TODO: 2019-04-27 Sin æœ?åŠ¡å?·ç”Ÿæˆ?è§„åˆ™
                .setServiceNumber(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16))
                .setOrderNo(checkOrder.getOrderNo())
                .setStatus(OrderReturnStatusEnum.RETURN_APPLICATION.getValue())
                .setCreateTime(new Date());

        // ä¿?å­˜ç”³è¯·ä¿¡æ?¯
        orderReturnMapper.insert(orderReturnDO);
        return CommonResult.success(null);
    }

    @Override
    public String updateRefundSuccess(String orderId, Integer refundPrice) {
        return "success";
    }

    @Override
    public CommonResult<OrderReturnInfoBO> orderApplyInfo(Integer orderId) {

        // æ£€æŸ¥è®¢å?•æ˜¯å?¦é€€è´§
        OrderReturnDO orderReturnDO = orderReturnMapper.selectByOrderId(orderId);
        if (orderReturnDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_NO_RETURN_APPLY.getCode());
        }

        List<OrderItemDO> orderItemDOList = orderItemMapper
                .selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(), orderId);

        // è®¢å?•ä¸?å­˜åœ¨
        if (CollectionUtils.isEmpty(orderItemDOList)) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
        }

        // è½¬æ?¢ returnInfo
        OrderReturnInfoBO.ReturnInfo returnInfo = OrderReturnConvert.INSTANCE.convert(orderReturnDO);
        List<OrderReturnInfoBO.OrderItem> itemList = OrderReturnConvert.INSTANCE.convert(orderItemDOList);

        // ç‰©æµ?ä¿¡æ?¯
        CommonResult<OrderLastLogisticsInfoBO> lastLogisticsCommonResult = orderLogisticsService
                .getLastLogisticsInfo(orderReturnDO.getOrderLogisticsId());

        if (lastLogisticsCommonResult.isError()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_LOGISTICS_INVOKING_FAIL.getCode());
        }

        OrderLastLogisticsInfoBO lastLogisticsInfoBO = lastLogisticsCommonResult.getData();
        OrderReturnInfoBO orderReturnInfoBO = new OrderReturnInfoBO()
                .setOrderItems(itemList)
                .setReturnInfo(returnInfo)
                .setLastLogisticsInfo(lastLogisticsInfoBO);

        return CommonResult.success(orderReturnInfoBO);
    }

    @Override
    public CommonResult<OrderReturnListBO> orderReturnList(OrderReturnQueryDTO queryDTO) {
        int totalCount = orderReturnMapper.selectListCount(queryDTO);
        if (totalCount <= 0) {
            return CommonResult.success(
                    new OrderReturnListBO()
                            .setData(Collections.EMPTY_LIST)
                            .setIndex(queryDTO.getIndex())
                            .setPageSize(queryDTO.getPageSize())
                            .setTotalCount(0)
            );
        }
        List<OrderReturnDO> orderReturnDOList = orderReturnMapper.selectList(queryDTO);
        List<OrderReturnListBO.OrderReturn> orderReturnListBOList
                = OrderReturnConvert.INSTANCE.convertListBO(orderReturnDOList);

        return CommonResult.success(
                new OrderReturnListBO()
                        .setData(orderReturnListBOList)
                        .setIndex(queryDTO.getIndex())
                        .setPageSize(queryDTO.getPageSize())
                        .setTotalCount(totalCount)
        );
    }

    @Override
    public CommonResult orderReturnAgree(Integer id) {
        OrderReturnDO orderReturnDO = orderReturnMapper.selectById(id);
        if (orderReturnDO == null) {
            return ServiceExceptionUtil
                    .error(OrderErrorCodeEnum.ORDER_RETURN_NOT_EXISTENT.getCode());
        }

        orderReturnMapper.updateById(
                new OrderReturnDO()
                        .setId(id)
                        .setApprovalTime(new Date())
                        .setStatus(OrderReturnStatusEnum.APPLICATION_SUCCESSFUL.getValue())
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult orderReturnRefuse(Integer id) {
        OrderReturnDO orderReturnDO = orderReturnMapper.selectById(id);
        if (orderReturnDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_NOT_EXISTENT.getCode());
        }

        orderReturnMapper.updateById(
                new OrderReturnDO()
                        .setId(id)
                        .setRefuseTime(new Date())
                        .setStatus(OrderReturnStatusEnum.APPLICATION_FAIL.getValue())
        );
        return CommonResult.success(null);
    }

    @Override
    public CommonResult confirmReceipt(Integer id) {
        OrderReturnDO orderReturnDO = orderReturnMapper.selectById(id);
        if (orderReturnDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_NOT_EXISTENT.getCode());
        }

        orderReturnMapper.updateById(
                new OrderReturnDO()
                        .setId(id)
                        .setReceiverTime(new Date())
                        .setStatus(OrderReturnStatusEnum.ORDER_RECEIPT.getValue())
        );
        return CommonResult.success(null);
    }

    @Override
    @Transactional
    public CommonResult refund(Integer id, String ip) {
        OrderReturnDO orderReturnDO = orderReturnMapper.selectById(id);
        if (orderReturnDO == null) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_NOT_EXISTENT.getCode());
        }

        // TODO: 2019/5/8 sin, å?‘é€? MQ æ¶ˆæ?¯ï¼Œç”³è¯·é€€è´§æˆ?åŠŸ!
        // TODO: 2019/5/8 sin é€€æ¬¾ï¼šæ”¯ä»˜ç³»ç»Ÿé€€æ¬¾
        // TODO: 2019/5/8 sin é€€è´§+é€€æ¬¾ï¼šé€€å›žå•†å“?ç­¾æ”¶å?Žï¼Œæ”¯ä»˜ç³»ç»Ÿé€€æ¬¾
        // TODO: 2019/5/8 sin äº‹åŠ¡ä¸€è‡´æ€§ [é‡?è¦?]


        CommonResult<DataDictBO> dataDictResult = dataDictService
                .getDataDict(DictKeyConstants.ORDER_RETURN_REASON, orderReturnDO.getReason());

        if (dataDictResult.isError()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.DICT_SERVER_INVOKING_FAIL.getCode());
        }

        // æ”¯ä»˜é€€æ¬¾
        String orderDescription = dataDictResult.getData()
                .getDisplayName() + "(" + orderReturnDO.getDescribe() + ")";

        CommonResult payResult = payRefundService.submitRefund(
                new PayRefundSubmitDTO()
                        .setAppId(PayAppId.APP_ID_SHOP_ORDER)
                        .setOrderId(String.valueOf(orderReturnDO.getOrderId()))
                        .setPrice(orderReturnDO.getRefundPrice())
                        .setOrderDescription(orderDescription)
                        .setCreateIp(ip)
        );

        if (!payResult.isSuccess()) {
            return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_REFUND_FAILED.getCode());
        }

        // æ›´æ–° è®¢å?•é€€è´§ ä¿¡æ?¯
        orderReturnMapper.updateById(
                new OrderReturnDO()
                        .setId(id)
                        .setClosingTime(new Date())
                        .setStatus(OrderReturnStatusEnum.RETURN_SUCCESS.getValue())
        );

        // æ›´æ–°è®¢å?•
        orderMapper.updateById(
                new OrderDO()
                        .setId(orderReturnDO.getOrderId())
                        .setClosingTime(new Date())
                        .setStatus(OrderStatusEnum.COMPLETED.getValue())
        );

        // æ›´æ–°è®¢å?•
        orderItemMapper.updateByOrderId(
                orderReturnDO.getOrderId(),
                new OrderItemDO()
                        .setClosingTime(new Date())
                        .setStatus(OrderStatusEnum.COMPLETED.getValue())
        );
        return CommonResult.success(null);
    }
}
