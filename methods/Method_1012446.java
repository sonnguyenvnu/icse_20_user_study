@Override public boolean grant(@NotEmpty List<Integer> roleIds,@NotEmpty List<Integer> menuIds){
  roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId,roleIds));
  List<RoleMenu> roleMenus=new ArrayList<>();
  roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
    RoleMenu roleMenu=new RoleMenu();
    roleMenu.setRoleId(roleId);
    roleMenu.setMenuId(menuId);
    roleMenus.add(roleMenu);
  }
));
  return roleMenuService.saveBatch(roleMenus);
}
