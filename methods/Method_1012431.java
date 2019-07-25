/** 
 * ??
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="code",value="????",paramType="query",dataType="string"),@ApiImplicitParam(name="name",value="????",paramType="query",dataType="string")}) @PreAuth(RoleConstant.HAS_ROLE_ADMIN) @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??menu") public R<List<MenuVO>> list(@ApiIgnore @RequestParam Map<String,Object> menu){
  @SuppressWarnings("unchecked") List<Menu> list=menuService.list(Condition.getQueryWrapper(menu,Menu.class).lambda().orderByAsc(Menu::getSort));
  return R.data(MenuWrapper.build().listNodeVO(list));
}
