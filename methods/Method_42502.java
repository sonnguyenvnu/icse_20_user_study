/** 
 * ????ID??????????.
 * @param idStr
 * @return
 */
public List<PmsPermission> findByIds(String idStr){
  List<String> ids=Arrays.asList(idStr.split(","));
  return this.getSessionTemplate().selectList(getStatement("findByIds"),ids);
}
