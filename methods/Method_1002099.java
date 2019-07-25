/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 6:31 PM
 */
@RequestMapping(value="/view/{roleId}") @ResponseBody public ResponseData view(@PathVariable Long roleId){
  if (ToolUtil.isEmpty(roleId)) {
    throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
  }
  Role role=this.roleService.getById(roleId);
  Map<String,Object> roleMap=BeanUtil.beanToMap(role);
  Long pid=role.getPid();
  String pName=ConstantFactory.me().getSingleRoleName(pid);
  roleMap.put("pName",pName);
  return ResponseData.success(roleMap);
}
