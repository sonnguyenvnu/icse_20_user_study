/** 
 * Cancel method boolean.
 * @param inventoryDTO the inventory dto
 * @return the boolean
 */
public Boolean cancelMethod(InventoryDTO inventoryDTO){
  LOGGER.info("==========??????????===========");
  inventoryMapper.cancel(inventoryDTO);
  return true;
}
