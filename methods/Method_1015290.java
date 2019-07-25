/** 
 * ??????
 * @return Result
 */
@RequestMapping(value="login/{userName}/{password}",method=RequestMethod.POST) @ApiOperation(value="??????",notes="?????????????",httpMethod="POST",produces=MediaType.APPLICATION_JSON_UTF8_VALUE) @ApiImplicitParams({@ApiImplicitParam(name="userName",value="???",required=true,dataType="Result",paramType="path"),@ApiImplicitParam(name="password",value="???",required=true,dataType="Result",paramType="path")}) @ApiResponses(value={@ApiResponse(code=404,message="Not Found"),@ApiResponse(code=400,message="No Name Provided")}) public Result login(@PathVariable("userName") String userName,@PathVariable("password") String password){
  AdminModel adminModel=service.findAdminUserByUserName(userName);
  if (adminModel == null) {
    return new Result(CodeConst.USER_NOT_FOUND.getResultCode(),CodeConst.USER_NOT_FOUND.getMessage());
  }
  if (!Md5Util.encode(password,adminModel.getSalt()).equals(adminModel.getPassword())) {
    return new Result(CodeConst.AUTH_FAILED.getResultCode(),CodeConst.AUTH_FAILED.getMessage());
  }
  return new Result<>(adminModel);
}
