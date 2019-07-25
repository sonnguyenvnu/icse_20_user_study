/** 
 * ??
 */
@GetMapping("/detail") @ApiOperationSupport(order=1) @ApiOperation(value="??",notes="??client") public R<AuthClient> detail(AuthClient authClient){
  AuthClient detail=clientService.getOne(Condition.getQueryWrapper(authClient));
  return R.data(detail);
}
