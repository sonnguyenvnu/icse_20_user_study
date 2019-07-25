/** 
 * ?????
 */
@GetMapping("/select") @ApiOperation(value="?????",notes="??tenant") public R<List<Tenant>> select(Tenant tenant,BladeUser bladeUser){
  QueryWrapper<Tenant> queryWrapper=Condition.getQueryWrapper(tenant);
  List<Tenant> list=tenantService.list((!bladeUser.getTenantCode().equals(BladeConstant.ADMIN_TENANT_CODE)) ? queryWrapper.lambda().eq(Tenant::getTenantCode,bladeUser.getTenantCode()) : queryWrapper);
  return R.data(list);
}
