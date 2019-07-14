public static int getViewInset(View view){
  if (view == null || Build.VERSION.SDK_INT < 21 || view.getHeight() == AndroidUtilities.displaySize.y || view.getHeight() == AndroidUtilities.displaySize.y - statusBarHeight) {
    return 0;
  }
  try {
    if (mAttachInfoField == null) {
      mAttachInfoField=View.class.getDeclaredField("mAttachInfo");
      mAttachInfoField.setAccessible(true);
    }
    Object mAttachInfo=mAttachInfoField.get(view);
    if (mAttachInfo != null) {
      if (mStableInsetsField == null) {
        mStableInsetsField=mAttachInfo.getClass().getDeclaredField("mStableInsets");
        mStableInsetsField.setAccessible(true);
      }
      Rect insets=(Rect)mStableInsetsField.get(mAttachInfo);
      return insets.bottom;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return 0;
}
