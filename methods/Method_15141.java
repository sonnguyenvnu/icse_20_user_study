/** 
 * @param clazz
 * @return
 */
public <T>String getListPath(Class<T> clazz){
  String classPath=getClassPath(clazz);
  return StringUtil.isNotEmpty(classPath,true) ? classPath + KEY_LIST : null;
}
