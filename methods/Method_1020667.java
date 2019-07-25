/** 
 * ??
 */
@RequestMapping("/auth/{id}") public String auth(@PathVariable String id,Model model){
  SysRole sysRole=sysRoleService.getById(id);
  if (sysRole == null) {
    throw new RuntimeException("??????");
  }
  List<SysRoleMenu> sysRoleMenus=sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id",id));
  List<String> menuIds=Lists.transform(sysRoleMenus,input -> input.getMenuId().toString());
  List<TreeMenuAllowAccess> treeMenuAllowAccesses=sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds,"0");
  model.addAttribute("sysRole",sysRole);
  model.addAttribute("treeMenuAllowAccesses",treeMenuAllowAccesses);
  return "ftl/admin/role/auth";
}
