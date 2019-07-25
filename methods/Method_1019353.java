@GetMapping("/list") @ApiOperation("???? Banner ??") public CommonResult<Map<Integer,Collection<UsersProductRecommendVO>>> list(){
  List<ProductRecommendBO> productRecommends=productRecommendService.getProductRecommendList(null,CommonStatusEnum.ENABLE.getValue());
  List<ProductSpuBO> spus=productSpuService.getProductSpuList(productRecommends.stream().map(ProductRecommendBO::getProductSpuId).collect(Collectors.toSet()));
  Map<Integer,ProductSpuBO> spuMap=spus.stream().collect(Collectors.toMap(ProductSpuBO::getId,account -> account));
  Multimap<Integer,UsersProductRecommendVO> result=HashMultimap.create();
  productRecommends.sort(Comparator.comparing(ProductRecommendBO::getSort));
  productRecommends.forEach(productRecommendBO -> result.put(productRecommendBO.getType(),ProductRecommendConvert.INSTANCE.convert(spuMap.get(productRecommendBO.getProductSpuId()))));
  return CommonResult.success(result.asMap());
}
