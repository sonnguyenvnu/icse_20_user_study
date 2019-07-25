@GetMapping("/info") @ApiOperation("?? SPU ??") @ApiImplicitParam(name="id",value="SPU ??",required=true,example="100") public CommonResult<UsersProductSpuDetailVO> info(@RequestParam("id") Integer id){
  return success(ProductSpuConvert.INSTANCE.convert4(productSpuService.getProductSpuDetail(id)));
}
