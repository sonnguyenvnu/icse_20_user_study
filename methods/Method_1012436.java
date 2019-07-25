/** 
 * ??
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="roleName",value="????",paramType="query",dataType="string"),@ApiImplicitParam(name="roleAlias",value="????",paramType="query",dataType="string")}) @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??role") public R<List<INode>> list(@ApiIgnore @RequestParam Map<String,Object> role,BladeUser bladeUser){
  QueryWrapper<Role> queryWrapper=Condition.getQueryWrapper(role,Role.class);
  List<Role> list=roleService.list((!bladeUser.getTenantCode().equals(BladeConstant.ADMIN_TENANT_CODE)) ? queryWrapper.lambda().eq(Role::getTenantCode,bladeUser.getTenantCode()) : queryWrapper);
  return R.data(RoleWrapper.build().listNodeVO(list));
}
