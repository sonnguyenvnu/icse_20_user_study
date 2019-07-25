/** 
 * ????????
 */
@GetMapping("/tree") @ApiOperationSupport(order=3) @ApiOperation(value="????",notes="????") public R<List<RoleVO>> tree(String tenantCode,BladeUser bladeUser){
  List<RoleVO> tree=roleService.tree(Func.toStr(tenantCode,bladeUser.getTenantCode()));
  return R.data(tree);
}
