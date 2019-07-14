/** 
 * ???????
 * @param appName      ???
 * @param key          ???
 * @param defaultValue ???
 * @return ??
 */
public static int getIntegerValue(String appName,String key,int defaultValue){
  String ret=getStringValue0(appName,key);
  return StringUtils.isEmpty(ret) ? defaultValue : CommonUtils.parseInt(ret,defaultValue);
}
