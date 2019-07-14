public static void requestAdjustResize(Activity activity,int classGuid){
  if (activity == null || isTablet()) {
    return;
  }
  activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
  adjustOwnerClassGuid=classGuid;
}
