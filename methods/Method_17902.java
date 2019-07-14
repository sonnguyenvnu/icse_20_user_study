static boolean shouldSkipRecyclingComponentHost(int mountIndex,String rootComponentName){
  final boolean isMountIndexBisectEnabled=ComponentsConfiguration.isMountIndexBisectEnabled;
  final boolean isRootComponentBisectEnabled=ComponentsConfiguration.isRootComponentBisectEnabled;
  if (!isMountIndexBisectEnabled && !isRootComponentBisectEnabled) {
    return false;
  }
  if (!isRootComponentBisectEnabled) {
    return passesMountIndexBisect(mountIndex);
  }
 else   if (!isMountIndexBisectEnabled) {
    return passesRootComponentBisect(rootComponentName);
  }
 else {
    return passesMountIndexBisect(mountIndex) && passesRootComponentBisect(rootComponentName);
  }
}
