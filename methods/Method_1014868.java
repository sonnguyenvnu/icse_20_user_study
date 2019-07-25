/** 
 * @Description : ????
 * @Param : [vo]
 * @return : [vo]
 * @Author : aodeng
 * @Date : 19-3-17
 */
@ApiOperation(value="????",notes="????????????pageNum?pageSize",produces="application/json, application/xml",consumes="application/json, application/xml",response=SysRole.class) @ApiImplicitParams({@ApiImplicitParam(name="pageNum",value="???",required=true,dataType="String",paramType="query"),@ApiImplicitParam(name="pageSize",value="????",required=true,dataType="String",paramType="query")}) @RequiresPermissions("role:list") @PostMapping("/list") public PageResultVo list(RoleConditionVo vo){
  PageInfo<SysRole> pageInfo=sysRoleService.findPageBreakByCondition(vo);
  return ResultHopeUtil.tablePage(pageInfo);
}
