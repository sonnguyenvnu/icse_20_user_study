/** 
 * ??
 */
@GetMapping("/detail") @PreAuth(RoleConstant.HAS_ROLE_ADMIN) @ApiOperationSupport(order=1) @ApiOperation(value="??",notes="??menu") public R<MenuVO> detail(Menu menu){
  Menu detail=menuService.getOne(Condition.getQueryWrapper(menu));
  return R.data(MenuWrapper.build().entityVO(detail));
}
