@Override public List<AppClientVersion> getAppAllServerClientVersion(long appId){
  List<AppClientVersion> appClientVersionList=getAppAllClientVersion(appId);
  if (CollectionUtils.isEmpty(appClientVersionList)) {
    return Collections.emptyList();
  }
  List<AppClientVersion> appClientVersionServerList=new ArrayList<AppClientVersion>();
  for (  AppClientVersion appClientVersion : appClientVersionList) {
    String clientIp=appClientVersion.getClientIp();
    String[] items=clientIp.split(".");
    if (!OFFICE_IP.contains(items[0] + "." + items[1])) {
      appClientVersionServerList.add(appClientVersion);
    }
  }
  return appClientVersionServerList;
}
