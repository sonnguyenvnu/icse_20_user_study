@Override @Hmily(confirmMethod="confirmMethodTimeout",cancelMethod="cancelMethod") @Transactional(rollbackFor=Exception.class) public Boolean mockWithConfirmTimeout(InventoryDTO inventoryDTO){
  LOGGER.info("==========??????????mockWithConfirmTimeout===========");
  final int decrease=inventoryMapper.decrease(inventoryDTO);
  if (decrease != 1) {
    throw new HmilyRuntimeException("????");
  }
  return true;
}
