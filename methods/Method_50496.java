/** 
 * Confirm method exception boolean.
 * @param inventoryDTO the inventory dto
 * @return the boolean
 */
@Transactional(rollbackFor=Exception.class) public Boolean confirmMethodException(InventoryDTO inventoryDTO){
  LOGGER.info("==========??????????===========");
  final int decrease=inventoryMapper.decrease(inventoryDTO);
  if (decrease != 1) {
    throw new HmilyRuntimeException("????");
  }
  return true;
}
