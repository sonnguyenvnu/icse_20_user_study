/** 
 * ?????http://localhost:8080/jeecg/rest/user
 * @return
 */
@RequestMapping(method=RequestMethod.GET) @ResponseBody @ApiOperation(value="??????",produces="application/json",httpMethod="GET") public List<TSUser> list(){
  List<TSUser> listUsers=userService.getList(TSUser.class);
  return listUsers;
}
