@RequestMapping("/mockWithTryException") public Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO){
  return inventoryService.mockWithTryException(inventoryDTO);
}
