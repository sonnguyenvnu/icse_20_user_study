/** 
 * ??
 */
@GetMapping("/detail") @ApiOperation(value="??",notes="??tenant") public R<Tenant> detail(Tenant tenant){
  Tenant detail=tenantService.getOne(Condition.getQueryWrapper(tenant));
  return R.data(detail);
}
