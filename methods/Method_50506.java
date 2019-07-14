private Order buildTestOrder(Integer count,BigDecimal amount){
  Order order=new Order();
  order.setCreateTime(new Date());
  order.setNumber(IdWorkerUtils.getInstance().buildPartNumber());
  order.setProductId("1");
  order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
  order.setTotalAmount(amount);
  order.setCount(count);
  order.setUserId("10000");
  return order;
}
