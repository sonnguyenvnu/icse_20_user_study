@GetMapping("/list") @ApiOperation("???? Banner ??") public CommonResult<List<UsersBannerVO>> list(){
  List<BannerBO> result=bannerService.getBannerListByStatus(CommonStatusEnum.ENABLE.getValue());
  result.sort(Comparator.comparing(BannerBO::getSort));
  return CommonResult.success(BannerConvert.USERS.convertList(result));
}
