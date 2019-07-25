/** 
 * @Description : ????
 * @Param : [vo]
 * @return : [vo]
 * @Author : aodeng
 * @Date : 19-3-17
 */
@ApiOperation(value="????",notes="????????????pageNum?pageSize",produces="application/json, application/xml",consumes="application/json, application/xml",response=SysUser.class) @ApiImplicitParams({@ApiImplicitParam(name="pageNum",value="???",required=true,dataType="String",paramType="query"),@ApiImplicitParam(name="pageSize",value="????",required=true,dataType="String",paramType="query")}) @RequiresPermissions("user:list") @PostMapping("/list") public PageResultVo list(UserConditionVo vo){
  PageInfo<SysUser> pageInfo=sysUserService.findPageBreakByCondition(vo);
  return ResultHopeUtil.tablePage(pageInfo);
}
