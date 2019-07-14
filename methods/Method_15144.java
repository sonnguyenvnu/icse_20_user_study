/** 
 * ????
 * @param clazz ?
 * @param group ??
 * @param map ???
 * @param pageSize ????
 */
public <T>void addList(Class<T> clazz,String group,LinkedHashMap<String,T> map,int pageSize){
  if (StringUtil.isNotEmpty(group,true) == false) {
    Log.e(TAG,"addList  StringUtil.isNotEmpty(group, true) == false >> return;");
    return;
  }
  saveList(clazz,group,map,-1,pageSize);
}
