@ApiOperation(value="??TOKEN") @RequestMapping(value="/{username}",method=RequestMethod.DELETE) @ResponseBody public ResponseMessage<?> logout(@ApiParam(name="username",value="????",required=true) @PathVariable("username") String username){
  logger.info("deleteToken[{}]",username);
  if (StringUtils.isEmpty(username)) {
    return Result.error("?????????!");
  }
  try {
    tokenManager.deleteToken(username);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return Result.error("??TOKEN??");
  }
  return Result.success();
}
