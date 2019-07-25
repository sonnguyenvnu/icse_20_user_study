private boolean reduce(List<String> pluginActivityList,String pluginActivityName){
  if (pluginActivityList != null && pluginActivityList.size() > 0 && pluginActivityList.get(0).equals(pluginActivityName)) {
    LogUtil.v("unBindLaunchModeStubActivity",pluginActivityName);
    pluginActivityList.remove(pluginActivityName);
    return true;
  }
  return false;
}
