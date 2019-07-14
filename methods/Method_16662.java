@GetMapping("/all") @Authorize(action=Permission.ACTION_QUERY) @ApiOperation("????????") public ResponseMessage<List<DistrictEntity>> all(){
  return ResponseMessage.ok(districtService.select());
}
