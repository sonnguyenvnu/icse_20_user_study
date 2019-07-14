@Override @Hmily(confirmMethod="confirmOrderStatus",cancelMethod="cancelOrderStatus") public String mockPaymentInventoryWithTryTimeout(Order order){
  LOGGER.debug("===========??springcloud  mockPaymentInventoryWithTryTimeout ??????==========");
  order.setStatus(OrderStatusEnum.PAYING.getCode());
  orderMapper.update(order);
  AccountDTO accountDTO=new AccountDTO();
  accountDTO.setAmount(order.getTotalAmount());
  accountDTO.setUserId(order.getUserId());
  accountClient.payment(accountDTO);
  InventoryDTO inventoryDTO=new InventoryDTO();
  inventoryDTO.setCount(order.getCount());
  inventoryDTO.setProductId(order.getProductId());
  inventoryClient.mockWithTryTimeout(inventoryDTO);
  return "success";
}
