public Boolean cancelMethod(InventoryDTO inventoryDTO){
  LOGGER.info("==========Springcloud??????????===========");
  int rows=inventoryMapper.cancel(inventoryDTO);
  return true;
}
