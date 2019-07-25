void save(FixedActivity fixedActivity,int launchMode){
  Set<String> fixedActivities=null;
  String tag=null;
  if (launchMode == ActivityInfo.LAUNCH_SINGLE_TOP) {
    fixedActivities=mSingleTopFixedActivities;
    tag=TAG_SINGLE_TOP;
  }
 else   if (launchMode == ActivityInfo.LAUNCH_SINGLE_INSTANCE) {
    fixedActivities=mSingleInstanceFixedActivities;
    tag=TAG_SINGLE_INSTANCE;
  }
 else   if (launchMode == ActivityInfo.LAUNCH_SINGLE_TASK) {
    fixedActivities=mSingleTaskFixedActivities;
    tag=TAG_SINGLE_TASK;
  }
  if (null != fixedActivities) {
    int size=fixedActivities.size();
    fixedActivities.add(fixedActivity.toString());
    if (fixedActivities.size() > size) {
      mSharedPreferences.edit().putStringSet(tag,fixedActivities).apply();
    }
  }
}
