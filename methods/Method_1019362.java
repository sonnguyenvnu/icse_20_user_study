@GetMapping("/info") @RequiresLogin @ApiOperation(value="????") public CommonResult<UsersUserVO> info(){
  UserBO userResult=userService.getUser(UserSecurityContextHolder.getContext().getUserId());
  return success(UserConvert.INSTANCE.convert2(userResult));
}
