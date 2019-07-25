@Override @Transactional public CommonResult refund(Integer id,String ip){
  OrderReturnDO orderReturnDO=orderReturnMapper.selectById(id);
  if (orderReturnDO == null) {
    return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_NOT_EXISTENT.getCode());
  }
  CommonResult<DataDictBO> dataDictResult=dataDictService.getDataDict(DictKeyConstants.ORDER_RETURN_REASON,orderReturnDO.getReason());
  if (dataDictResult.isError()) {
    return ServiceExceptionUtil.error(OrderErrorCodeEnum.DICT_SERVER_INVOKING_FAIL.getCode());
  }
  String orderDescription=dataDictResult.getData().getDisplayName() + "(" + orderReturnDO.getDescribe() + ")";
  CommonResult payResult=payRefundService.submitRefund(new PayRefundSubmitDTO().setAppId(PayAppId.APP_ID_SHOP_ORDER).setOrderId(String.valueOf(orderReturnDO.getOrderId())).setPrice(orderReturnDO.getRefundPrice()).setOrderDescription(orderDescription).setCreateIp(ip));
  if (!payResult.isSuccess()) {
    return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_RETURN_REFUND_FAILED.getCode());
  }
  orderReturnMapper.updateById(new OrderReturnDO().setId(id).setClosingTime(new Date()).setStatus(OrderReturnStatusEnum.RETURN_SUCCESS.getValue()));
  orderMapper.updateById(new OrderDO().setId(orderReturnDO.getOrderId()).setClosingTime(new Date()).setStatus(OrderStatusEnum.COMPLETED.getValue()));
  orderItemMapper.updateByOrderId(orderReturnDO.getOrderId(),new OrderItemDO().setClosingTime(new Date()).setStatus(OrderStatusEnum.COMPLETED.getValue()));
  return CommonResult.success(null);
}
