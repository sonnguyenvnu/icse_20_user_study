public static void removeAdjustResize(Activity activity,int classGuid){
  if (activity == null || isTablet()) {
    return;
  }
  if (adjustOwnerClassGuid == classGuid) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
  }
}
