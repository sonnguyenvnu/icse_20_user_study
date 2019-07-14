@Override public void testMakePayment(Order order){
  AccountDTO accountDTO=new AccountDTO();
  accountDTO.setAmount(order.getTotalAmount());
  accountDTO.setUserId(order.getUserId());
  accountService.testPayment(accountDTO);
  InventoryDTO inventoryDTO=new InventoryDTO();
  inventoryDTO.setCount(order.getCount());
  inventoryDTO.setProductId(order.getProductId());
  inventoryService.testDecrease(inventoryDTO);
}
