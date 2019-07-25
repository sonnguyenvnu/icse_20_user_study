/** 
 * ??
 */
@GetMapping("/detail") @ApiOperation(value="??",notes="??code") public R<Code> detail(Code code){
  Code detail=codeService.getOne(Condition.getQueryWrapper(code));
  return R.data(detail);
}
