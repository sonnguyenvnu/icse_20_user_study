void init(){
  mSingleTopFixedActivities=mSharedPreferences.getStringSet(TAG_SINGLE_TOP,new HashSet<String>());
  mSingleInstanceFixedActivities=mSharedPreferences.getStringSet(TAG_SINGLE_INSTANCE,new HashSet<String>());
  mSingleTaskFixedActivities=mSharedPreferences.getStringSet(TAG_SINGLE_TASK,new HashSet<String>());
}
