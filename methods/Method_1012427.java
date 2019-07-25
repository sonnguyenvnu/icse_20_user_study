/** 
 * ??
 */
@GetMapping("/detail") @ApiOperationSupport(order=1) @ApiOperation(value="??",notes="??dept") public R<DeptVO> detail(Dept dept){
  Dept detail=deptService.getOne(Condition.getQueryWrapper(dept));
  return R.data(DeptWrapper.build().entityVO(detail));
}
