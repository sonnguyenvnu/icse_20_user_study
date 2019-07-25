/** 
 * ????????
 * @return
 */
@GetMapping("/tree") @ApiOperationSupport(order=3) @ApiOperation(value="????",notes="????") public R<List<DeptVO>> tree(String tenantCode,BladeUser bladeUser){
  List<DeptVO> tree=deptService.tree(Func.toStr(tenantCode,bladeUser.getTenantCode()));
  return R.data(tree);
}
