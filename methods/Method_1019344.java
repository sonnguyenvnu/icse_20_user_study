@Override public CommonResult<OrderInfoBO> info(Integer userId,Integer orderId){
  OrderDO orderDO=orderMapper.selectById(orderId);
  if (orderDO == null) {
    return ServiceExceptionUtil.error(OrderErrorCodeEnum.ORDER_NOT_EXISTENT.getCode());
  }
  List<OrderItemDO> itemDOList=orderItemMapper.selectByDeletedAndOrderId(DeletedStatusEnum.DELETED_NO.getValue(),orderId);
  List<OrderInfoBO.OrderItem> orderItems=OrderItemConvert.INSTANCE.convertOrderInfoWithOrderItem(itemDOList);
  Set<Integer> orderLogisticsIds=itemDOList.stream().filter(o -> o.getOrderLogisticsId() != null).map(o -> o.getOrderLogisticsId()).collect(Collectors.toSet());
  OrderRecipientDO orderRecipientDO=orderRecipientMapper.selectByOrderId(orderId);
  OrderLogisticsDetailDO orderLogisticsDetailDO=null;
  if (!CollectionUtils.isEmpty(orderLogisticsIds)) {
    orderLogisticsDetailDO=orderLogisticsDetailMapper.selectLast(orderLogisticsIds);
  }
  OrderReturnDO orderReturnDO=orderReturnMapper.selectByOrderId(orderId);
  OrderInfoBO.LogisticsDetail logisticsDetail=OrderLogisticsDetailConvert.INSTANCE.convertLogisticsDetail(orderLogisticsDetailDO);
  OrderInfoBO.Recipient recipient=OrderRecipientConvert.INSTANCE.convertOrderInfoRecipient(orderRecipientDO);
  OrderInfoBO orderInfoBO=OrderConvert.INSTANCE.convert(orderDO);
  orderInfoBO.setRecipient(recipient);
  orderInfoBO.setOrderItems(orderItems);
  orderInfoBO.setLatestLogisticsDetail(logisticsDetail);
  if (orderReturnDO != null) {
    orderInfoBO.setHasOrderReturn(orderReturnDO.getStatus());
  }
 else {
    orderInfoBO.setHasOrderReturn(-1);
  }
  return CommonResult.success(orderInfoBO);
}
