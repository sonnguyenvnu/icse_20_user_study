@Transactional(rollbackFor=Exception.class) public void saveRoleMenu(Long roleId,String roleMenuStr){
  pmsMenuRoleDao.deleteByRoleId(roleId);
  if (!StringUtils.isEmpty(roleMenuStr)) {
    String[] menuIds=roleMenuStr.split(",");
    for (int i=0; i < menuIds.length; i++) {
      Long menuId=Long.valueOf(menuIds[i]);
      PmsMenuRole item=new PmsMenuRole();
      item.setMenuId(menuId);
      item.setRoleId(roleId);
      pmsMenuRoleDao.insert(item);
    }
  }
}
