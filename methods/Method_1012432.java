/** 
 * ??????
 */
@GetMapping("/routes") @ApiOperationSupport(order=5) @ApiOperation(value="??????",notes="??????") public R<List<MenuVO>> routes(BladeUser user){
  List<MenuVO> list=menuService.routes(user.getRoleId());
  return R.data(list);
}
