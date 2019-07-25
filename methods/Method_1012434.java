/** 
 * ??
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="paramName",value="????",paramType="query",dataType="string"),@ApiImplicitParam(name="paramKey",value="????",paramType="query",dataType="string"),@ApiImplicitParam(name="paramValue",value="????",paramType="query",dataType="string")}) @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??param") public R<IPage<Param>> list(@ApiIgnore @RequestParam Map<String,Object> param,Query query){
  IPage<Param> pages=paramService.page(Condition.getPage(query),Condition.getQueryWrapper(param,Param.class));
  return R.data(pages);
}
