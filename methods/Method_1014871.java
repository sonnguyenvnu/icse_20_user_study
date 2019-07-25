/** 
 * @Description : ??????
 * @Param : [sysUserFrom]
 * @return : [sysUserFrom]
 * @Author : aodeng
 * @Date : 19-3-17
 */
@ApiOperation(value="??????",notes="??????",produces="application/json, application/xml",consumes="application/json, application/xml") @ApiResponses({@ApiResponse(code=200,message="????"),@ApiResponse(code=500,message="???????????")}) @RequiresPermissions("user:edit") @PostMapping("/edit") public ResponseVo edit(SysUser sysUserFrom){
  int a=sysUserService.updateByUserId(sysUserFrom);
  if (a > 0) {
    return ResultHopeUtil.success("???????");
  }
 else {
    return ResultHopeUtil.error("???????");
  }
}
