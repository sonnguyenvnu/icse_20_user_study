public static void setAdjustResizeToNothing(Activity activity,int classGuid){
  if (activity == null || isTablet()) {
    return;
  }
  if (adjustOwnerClassGuid == classGuid) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
  }
}
