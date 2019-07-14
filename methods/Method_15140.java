/** 
 * @param clazz
 * @return
 */
public <T>String getClassPath(Class<T> clazz){
  return clazz == null ? null : CACHE_PATH + clazz.getName();
}
