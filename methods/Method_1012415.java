/** 
 * ??
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="category",value="????",paramType="query",dataType="integer"),@ApiImplicitParam(name="title",value="????",paramType="query",dataType="string")}) @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??notice") public R<IPage<NoticeVO>> list(@ApiIgnore @RequestParam Map<String,Object> notice,Query query){
  IPage<Notice> pages=noticeService.page(Condition.getPage(query),Condition.getQueryWrapper(notice,Notice.class));
  return R.data(NoticeWrapper.build().pageVO(pages));
}
