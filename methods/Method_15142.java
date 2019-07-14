/** 
 * @param clazz
 * @param group
 * @return
 */
public <T>String getGroupPath(Class<T> clazz){
  String classPath=getClassPath(clazz);
  return StringUtil.isNotEmpty(classPath,true) == false ? null : classPath + KEY_GROUP;
}
