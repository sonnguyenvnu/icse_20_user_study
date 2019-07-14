/** 
 * ????????????
 */
public void loadResourceDefine(){
  map=new HashMap<>();
  Collection<ConfigAttribute> array;
  ConfigAttribute cfg;
  List<Permission> permissions=permissionDao.findAll();
  for (  Permission permission : permissions) {
    array=new ArrayList<>();
    cfg=new SecurityConfig(permission.getName());
    array.add(cfg);
    map.put(permission.getUrl(),array);
  }
}
