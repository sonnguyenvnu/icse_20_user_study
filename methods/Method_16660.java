@GetMapping("/children/{parentId}") @Authorize(action=Permission.ACTION_QUERY) @ApiOperation("????????") public ResponseMessage<List<DistrictEntity>> getByParentId(@PathVariable String parentId){
  return ResponseMessage.ok(districtService.selectChildNode(parentId));
}
