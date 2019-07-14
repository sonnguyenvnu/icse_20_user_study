/** 
 * ??????????????null
 * @param methodName ???
 * @param configKey  ??key?????
 * @return ??? method config value
 */
public Object getMethodConfigValue(String methodName,String configKey){
  if (configValueCache == null) {
    return null;
  }
  String key=buildmkey(methodName,configKey);
  return configValueCache.get(key);
}
