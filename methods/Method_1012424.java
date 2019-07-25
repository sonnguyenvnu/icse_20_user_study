/** 
 * ?? 
 */
@GetMapping("/list") @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??client") public R<IPage<AuthClient>> list(AuthClient authClient,Query query){
  IPage<AuthClient> pages=clientService.page(Condition.getPage(query),Condition.getQueryWrapper(authClient));
  return R.data(pages);
}
