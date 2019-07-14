@GetMapping("/children/{parentId}/all") @Authorize(action=Permission.ACTION_QUERY) @ApiOperation("??????????") public ResponseMessage<List<DistrictEntity>> getAllByParentId(@PathVariable String parentId){
  return ResponseMessage.ok(districtService.selectAllChildNode(parentId));
}
