/** 
 * ??id??
 * @param clazz
 * @param group
 * @return
 */
public <T>List<String> getIdList(Class<T> clazz,String group){
  SharedPreferences sp=getSharedPreferences(getClassPath(clazz) + KEY_GROUP);
  return sp == null ? null : JSON.parseArray(sp.getString(StringUtil.getTrimedString(group),null),String.class);
}
