/** 
 * ??????
 * @param remoteKey ??????
 * @return ????
 */
public String getModuleName(String remoteKey){
  AppInfo appInfo=appNames.get(remoteKey);
  return appInfo == null ? null : appInfo.getAppName();
}
