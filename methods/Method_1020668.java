/** 
 * ??
 */
@RequestMapping("/auth/{id}") public String auth(@PathVariable String id,Model model){
  SysSetting sysSetting=baseService.getById(id);
  if (sysSetting == null) {
    throw new RuntimeException("??????");
  }
  List<SysSettingMenu> sysSettingMenus=sysSettingMenuService.list(new QueryWrapper<SysSettingMenu>().eq("sys_id",id));
  List<String> menuIds=Lists.transform(sysSettingMenus,input -> input.getMenuId().toString());
  List<TreeMenuAllowAccess> treeMenuAllowAccesses=sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds,"0");
  model.addAttribute("sysSetting",sysSetting);
  model.addAttribute("treeMenuAllowAccesses",treeMenuAllowAccesses);
  return "ftl/admin/setting/auth";
}
