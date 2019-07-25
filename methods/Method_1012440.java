/** 
 * ?????
 */
@GetMapping("/page") @ApiOperation(value="??",notes="??tenant") public R<IPage<Tenant>> page(Tenant tenant,Query query){
  IPage<Tenant> pages=tenantService.selectTenantPage(Condition.getPage(query),tenant);
  return R.data(pages);
}
