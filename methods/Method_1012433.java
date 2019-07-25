/** 
 * ??????
 */
@GetMapping("/buttons") @ApiOperationSupport(order=6) @ApiOperation(value="??????",notes="??????") public R<List<MenuVO>> buttons(BladeUser user){
  List<MenuVO> list=menuService.buttons(user.getRoleId());
  return R.data(list);
}
