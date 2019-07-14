@Override @Hmily(confirmMethod="confirmMethod",cancelMethod="cancelMethod") public String mockWithTryException(InventoryDTO inventoryDTO){
  throw new HmilyRuntimeException("???????");
}
