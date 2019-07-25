/** 
 * @Description : ????????
 * @Param : [id, menuIds]
 * @return : [id, menuIds]
 * @Author : aodeng
 * @Date : 19-3-17
 */
@ApiOperation(value="????????",notes="????????") @ApiImplicitParams({@ApiImplicitParam(name="id",value="??id",required=true,dataType="String",paramType="query"),@ApiImplicitParam(name="menuIds",value="??id,String?????",required=true,dataType="String",paramType="query")}) @ApiResponses({@ApiResponse(code=200,message="????"),@ApiResponse(code=500,message="???????????")}) @RequiresPermissions("role:assign") @PostMapping("/assign") public ResponseVo assign(String id,String[] menuIds){
  log.info(menuIds + "--------" + id);
  List<String> resourceIds=new ArrayList<>();
  if (menuIds.length != 0) {
    resourceIds=Arrays.asList(menuIds);
  }
  ResponseVo responseVo=sysRoleService.addAssignResourceById(id,resourceIds);
  shiroService.reloadAuthorizingByRoleId(Convert.convert(Integer.class,id));
  return responseVo;
}
