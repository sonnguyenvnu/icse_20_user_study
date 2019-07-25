@GetMapping("/page") @ApiOperation(value="??????") @ApiImplicitParams({@ApiImplicitParam(name="type",value="????",example="1"),@ApiImplicitParam(name="pageNo",value="???? 1 ??",example="1"),@ApiImplicitParam(name="pageSize",value="????",required=true,example="10")}) public CommonResult<AdminsProductRecommendPageVO> page(@RequestParam(value="type",required=false) Integer type,@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
  ProductRecommendPageBO result=productRecommendService.getProductRecommendPage(new ProductRecommendPageDTO().setType(type).setPageNo(pageNo).setPageSize(pageSize));
  List<ProductSpuBO> spus=productSpuService.getProductSpuList(result.getList().stream().map(ProductRecommendBO::getProductSpuId).collect(Collectors.toSet()));
  Map<Integer,ProductSpuBO> spuMap=spus.stream().collect(Collectors.toMap(ProductSpuBO::getId,account -> account));
  AdminsProductRecommendPageVO response=ProductRecommendConvert.INSTANCE.convert(result);
  response.getList().forEach(recommendVO -> recommendVO.setProductSpuName(spuMap.get(recommendVO.getProductSpuId()).getName()));
  return CommonResult.success(response);
}
