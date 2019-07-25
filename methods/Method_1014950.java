/** 
 * @author wyq??
 */
@Before(Tx.class) public void copy(Integer roleId){
  AdminRole adminRole=AdminRole.dao.findById(roleId);
  List<Record> recordList=Db.find(Db.getSql("admin.role.getMenuIdsList"),roleId);
  List<Integer> menuIdsList=new ArrayList<>(recordList.size());
  for (  Record record : recordList) {
    menuIdsList.add(record.getInt("menu_id"));
  }
  String roleName=adminRole.getRoleName().trim();
  String pre=ReUtil.delFirst("[(]\\d+[)]$",roleName);
  List<AdminRole> adminRoleList;
  if (!ReUtil.contains("^[(]\\d+[)]$",roleName)) {
    adminRoleList=AdminRole.dao.find("select * from 72crm_admin_role where role_name like '" + pre + "%'");
  }
 else {
    adminRoleList=AdminRole.dao.find("select * from 72crm_admin_role where role_name regexp '^[(]\\d+[)]$'");
  }
  StringBuffer numberSb=new StringBuffer();
  for (  AdminRole dbAdminRole : adminRoleList) {
    String endCode=ReUtil.get("[(]\\d+[)]$",dbAdminRole.getRoleName(),0);
    if (endCode != null) {
      numberSb.append(endCode);
    }
  }
  int i=1;
  if (numberSb.length() == 0) {
    while (numberSb.toString().contains("(" + i + ")")) {
      i++;
    }
  }
  adminRole.setRoleName(pre + "(" + i + ")");
  adminRole.setRoleId(null);
  adminRole.save();
  Integer copyRoleId=adminRole.getInt("role_id");
  adminMenuService.saveRoleMenu(copyRoleId,adminRole.getDataType(),menuIdsList);
}
