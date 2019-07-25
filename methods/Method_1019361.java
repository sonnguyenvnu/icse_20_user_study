@GetMapping("/page") @ApiOperation(value="????") @ApiImplicitParams({@ApiImplicitParam(name="nickname",value="???????",example="??"),@ApiImplicitParam(name="pageNo",value="???? 1 ??",example="1"),@ApiImplicitParam(name="pageSize",value="????",required=true,example="10")}) public CommonResult<AdminsUserPageVO> page(@RequestParam(value="nickname",required=false) String nickname,@RequestParam(value="status",required=false) Integer status,@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
  UserPageDTO userPageDTO=new UserPageDTO().setNickname(nickname).setStatus(status).setPageNo(pageNo).setPageSize(pageSize);
  UserPageBO result=userService.getUserPage(userPageDTO);
  return success(UserConvert.INSTANCE.convert(result));
}
