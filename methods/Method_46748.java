/** 
 * ??????
 * @param remoteKey  ????
 * @param appName  ????
 * @param labelName TC????
 */
public void bindModuleName(String remoteKey,String appName,String labelName) throws RpcException {
  AppInfo appInfo=new AppInfo();
  appInfo.setAppName(appName);
  appInfo.setLabelName(labelName);
  appInfo.setCreateTime(new Date());
  if (containsLabelName(labelName)) {
    throw new RpcException("labelName:" + labelName + " has exist.");
  }
  appNames.put(remoteKey,appInfo);
}
