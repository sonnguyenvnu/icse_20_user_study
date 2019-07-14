@Override @Hmily(confirmMethod="confirmOrderStatus",cancelMethod="cancelOrderStatus") public String mockPaymentInventoryWithTryException(Order order){
  LOGGER.debug("===========??springcloud  mockPaymentInventoryWithTryException ??????==========");
  order.setStatus(OrderStatusEnum.PAYING.getCode());
  orderMapper.update(order);
  AccountDTO accountDTO=new AccountDTO();
  accountDTO.setAmount(order.getTotalAmount());
  accountDTO.setUserId(order.getUserId());
  accountClient.payment(accountDTO);
  InventoryDTO inventoryDTO=new InventoryDTO();
  inventoryDTO.setCount(order.getCount());
  inventoryDTO.setProductId(order.getProductId());
  inventoryClient.mockWithTryException(inventoryDTO);
  return "success";
}
