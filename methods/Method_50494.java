@Override @Hmily(confirmMethod="confirmMethodException",cancelMethod="cancelMethod") @Transactional(rollbackFor=Exception.class) public String mockWithConfirmException(InventoryDTO inventoryDTO){
  final int decrease=inventoryMapper.decrease(inventoryDTO);
  if (decrease != 1) {
    throw new HmilyRuntimeException("????");
  }
  return "success";
}
