@GetMapping("/code/{code}") @Authorize(action=Permission.ACTION_QUERY) @ApiOperation("??????????") public ResponseMessage<DistrictEntity> getByCode(@PathVariable String code){
  return ResponseMessage.ok(districtService.selectByCode(code));
}
