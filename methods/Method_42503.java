/** 
 * ?????????
 * @param permission
 * @return
 */
public PmsPermission getByPermission(String permission){
  return this.getSessionTemplate().selectOne(getStatement("getByPermission"),permission);
}
