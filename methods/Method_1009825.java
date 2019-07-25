/** 
 * ??Activity????
 */
void dump(){
  for (  ActivityRecord record : mUsedActivities.values()) {
    VLog.w("%s  -->  %s",record.mProxyActivity,record.mPluginActivity);
  }
  for (  String unusedActivity : mUnusedActivities) {
    VLog.w("%s  -->",unusedActivity);
  }
}
