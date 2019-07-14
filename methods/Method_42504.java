/** 
 * ??????????????
 * @param valueOf
 * @return
 */
public List<PmsPermission> listAllByMenuId(Long menuId){
  Map<String,Object> param=new HashMap<String,Object>();
  param.put("menuId",menuId);
  return this.getSessionTemplate().selectList(getStatement("listAllByMenuId"),param);
}
