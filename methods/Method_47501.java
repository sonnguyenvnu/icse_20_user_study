/** 
 * ??spring data jpa ???? api :localhost:8099/users/byUserNameContain?username=xxx ?????????
 * @param request
 * @return
 */
@RequestMapping(value="/byUserNameContain",method=RequestMethod.GET) @ResponseBody @ApiImplicitParam(paramType="query",name="username",value="???",dataType="string") @ApiOperation(value="?????????????",notes="??????") public ResponseEntity<Object> getUsers(HttpServletRequest request){
  Map<String,Object> map=CommonUtil.getParameterMap(request);
  String username=(String)map.get("username");
  return new ResponseEntity<>(userService.getByUsernameContaining(username),HttpStatus.OK);
}
