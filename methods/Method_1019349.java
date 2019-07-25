@GetMapping("/list") @ApiOperation("??????????????") @ApiImplicitParam(name="pid",value="??????",required=true,example="0") public CommonResult<List<UsersProductCategoryVO>> list(@RequestParam("pid") Integer pid){
  List<ProductCategoryBO> result=productCategoryService.getListByPid(pid);
  return CommonResult.success(ProductCategoryConvert.Users.INSTANCE.convertToVO(result));
}
