@Override @Hmily(confirmMethod="confirmOrderStatus",cancelMethod="cancelOrderStatus") public String mockPaymentInventoryWithConfirmTimeout(Order order){
  order.setStatus(OrderStatusEnum.PAYING.getCode());
  orderMapper.update(order);
  AccountDTO accountDTO=new AccountDTO();
  accountDTO.setAmount(order.getTotalAmount());
  accountDTO.setUserId(order.getUserId());
  accountService.payment(accountDTO);
  inventoryService.mockWithConfirmTimeout(new InventoryDTO());
  return "success";
}
