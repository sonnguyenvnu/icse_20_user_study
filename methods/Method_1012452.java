/** 
 * ????
 */
@GetMapping("/list") @ApiImplicitParams({@ApiImplicitParam(name="account",value="???",paramType="query",dataType="string"),@ApiImplicitParam(name="realName",value="??",paramType="query",dataType="string")}) @ApiOperationSupport(order=2) @ApiOperation(value="??",notes="??account?realName") public R<IPage<UserVO>> list(@ApiIgnore @RequestParam Map<String,Object> user,Query query,BladeUser bladeUser){
  QueryWrapper<User> queryWrapper=Condition.getQueryWrapper(user,User.class);
  IPage<User> pages=userService.page(Condition.getPage(query),(!bladeUser.getTenantCode().equals(BladeConstant.ADMIN_TENANT_CODE)) ? queryWrapper.lambda().eq(User::getTenantCode,bladeUser.getTenantCode()) : queryWrapper);
  return R.data(UserWrapper.build().pageVO(pages));
}
