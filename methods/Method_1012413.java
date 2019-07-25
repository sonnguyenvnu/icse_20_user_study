/** 
 * ??
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="codeName",value="???",paramType="query",dataType="string"),@ApiImplicitParam(name="tableName",value="??",paramType="query",dataType="string"),@ApiImplicitParam(name="modelName",value="???",paramType="query",dataType="string")}) @ApiOperation(value="??",notes="??code") public R<IPage<Code>> list(@ApiIgnore @RequestParam Map<String,Object> code,Query query){
  IPage<Code> pages=codeService.page(Condition.getPage(query),Condition.getQueryWrapper(code,Code.class));
  return R.data(pages);
}
