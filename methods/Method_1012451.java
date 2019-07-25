/** 
 * ????
 */
@ApiOperationSupport(order=1) @ApiOperation(value="????",notes="??id") @GetMapping("/detail") public R<UserVO> detail(User user){
  User detail=userService.getOne(Condition.getQueryWrapper(user));
  return R.data(UserWrapper.build().entityVO(detail));
}
