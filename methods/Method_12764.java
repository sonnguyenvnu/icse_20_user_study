public String getStubActivity(String className,int launchMode,Theme theme){
  String stubActivity=mCachedStubActivity.get(className);
  if (stubActivity != null) {
    return stubActivity;
  }
  TypedArray array=theme.obtainStyledAttributes(new int[]{android.R.attr.windowIsTranslucent,android.R.attr.windowBackground});
  boolean windowIsTranslucent=array.getBoolean(0,false);
  array.recycle();
  if (Constants.DEBUG) {
    Log.d(Constants.TAG_PREFIX + "StubActivityInfo","getStubActivity, is transparent theme ? " + windowIsTranslucent);
  }
  stubActivity=String.format(STUB_ACTIVITY_STANDARD,corePackage,usedStandardStubActivity);
switch (launchMode) {
case ActivityInfo.LAUNCH_MULTIPLE:
{
      stubActivity=String.format(STUB_ACTIVITY_STANDARD,corePackage,usedStandardStubActivity);
      if (windowIsTranslucent) {
        stubActivity=String.format(STUB_ACTIVITY_STANDARD,corePackage,2);
      }
      break;
    }
case ActivityInfo.LAUNCH_SINGLE_TOP:
{
    usedSingleTopStubActivity=usedSingleTopStubActivity % MAX_COUNT_SINGLETOP + 1;
    stubActivity=String.format(STUB_ACTIVITY_SINGLETOP,corePackage,usedSingleTopStubActivity);
    break;
  }
case ActivityInfo.LAUNCH_SINGLE_TASK:
{
  usedSingleTaskStubActivity=usedSingleTaskStubActivity % MAX_COUNT_SINGLETASK + 1;
  stubActivity=String.format(STUB_ACTIVITY_SINGLETASK,corePackage,usedSingleTaskStubActivity);
  break;
}
case ActivityInfo.LAUNCH_SINGLE_INSTANCE:
{
usedSingleInstanceStubActivity=usedSingleInstanceStubActivity % MAX_COUNT_SINGLEINSTANCE + 1;
stubActivity=String.format(STUB_ACTIVITY_SINGLEINSTANCE,corePackage,usedSingleInstanceStubActivity);
break;
}
default :
break;
}
mCachedStubActivity.put(className,stubActivity);
return stubActivity;
}
