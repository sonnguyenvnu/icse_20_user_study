/** 
 * ????
 */
@RequestMapping("/edit/{id}") public String edit(@PathVariable String id,Model model){
  SysUser sysUser=sysUserService.getById(id);
  List<SysRole> sysRoles=sysRoleService.list(null);
  QueryWrapper<SysUserRole> ew=new QueryWrapper<SysUserRole>();
  ew.eq("user_id ",id);
  List<SysUserRole> mySysUserRoles=sysUserRoleService.list(ew);
  List<String> myRolds=Lists.transform(mySysUserRoles,input -> input.getRoleId().toString());
  model.addAttribute("sysUser",sysUser);
  model.addAttribute("sysRoles",sysRoles);
  model.addAttribute("myRolds",myRolds);
  model.addAttribute("deptList",sysDeptService.list(null));
  return "ftl/admin/user/edit";
}
