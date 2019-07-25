/** 
 * ????
 */
public JSONObject auth(Long userId){
  JSONObject jsonObject=CaffeineCache.ME.get("role:permissions",userId.toString());
  if (jsonObject != null) {
    return jsonObject;
  }
  jsonObject=new JSONObject();
  List<Record> menuRecords;
  List<Integer> roleIds=queryRoleIdsByUserId(userId);
  if (roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
    menuRecords=adminMenuService.queryAllMenu();
  }
 else {
    menuRecords=adminMenuService.queryMenuByUserId(userId);
  }
  List<AdminMenu> adminMenus=adminMenuService.queryMenuByParentId(0);
  for (  AdminMenu adminMenu : adminMenus) {
    JSONObject object=new JSONObject();
    List<AdminMenu> adminMenuList=adminMenuService.queryMenuByParentId(adminMenu.getMenuId());
    for (    AdminMenu menu : adminMenuList) {
      JSONObject authObject=new JSONObject();
      for (      Record record : menuRecords) {
        if (menu.getMenuId().equals(record.getInt("parent_id"))) {
          authObject.put(record.getStr("realm"),true);
        }
      }
      if (!authObject.isEmpty()) {
        object.put(menu.getRealm(),authObject);
      }
    }
    if (adminMenu.getMenuId().equals(3)) {
      if (roleIds.contains(2) || roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
        object.put("system",true);
        object.put("user",true);
        object.put("examineFlow",true);
        object.put("oa",true);
        object.put("crm",true);
        object.put("permission",true);
      }
      if (roleIds.contains(3) || roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
        object.put("user",true);
      }
      if (roleIds.contains(4) || roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
        object.put("examineFlow",true);
      }
      if (roleIds.contains(5) || roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
        object.put("oa",true);
      }
      if (roleIds.contains(6) || roleIds.contains(BaseConstant.SUPER_ADMIN_ROLE_ID)) {
        object.put("crm",true);
      }
    }
    if (!object.isEmpty()) {
      jsonObject.put(adminMenu.getRealm(),object);
    }
  }
  CaffeineCache.ME.put("role:permissions:" + userId.toString(),jsonObject);
  return jsonObject;
}
