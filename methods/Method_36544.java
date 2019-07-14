/** 
 * create ComponentName by class type and unique id
 * @param clazz
 * @param uniqueId
 */
private static String mergeComponentName(Class<?> clazz,String uniqueId){
  String ret=clazz.getName();
  if (StringUtils.hasText(uniqueId)) {
    ret+=":" + uniqueId;
  }
  return ret;
}
