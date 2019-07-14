@RequestMapping("/findByProductId") public Integer findByProductId(@RequestParam("productId") String productId){
  return inventoryService.findByProductId(productId).getTotalInventory();
}
