public boolean containsLabelName(String moduleName){
  Set<String> keys=appNames.keySet();
  for (  String key : keys) {
    AppInfo appInfo=appNames.get(key);
    if (moduleName.equals(appInfo.getAppName())) {
      return true;
    }
  }
  return false;
}
