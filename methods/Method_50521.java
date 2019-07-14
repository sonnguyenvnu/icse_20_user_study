@Override @Hmily(confirmMethod="confirmMethod",cancelMethod="cancelMethod") @Transactional public Boolean mockWithTryException(InventoryDTO inventoryDTO){
  throw new HmilyRuntimeException("???????");
}
