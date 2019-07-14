@RequestMapping("/mockWithTryTimeout") public Boolean mockWithTryTimeout(@RequestBody InventoryDTO inventoryDTO){
  return inventoryService.mockWithTryTimeout(inventoryDTO);
}
