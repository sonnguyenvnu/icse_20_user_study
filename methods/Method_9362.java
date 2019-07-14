private void fixLayoutInternal(){
  if (dropDownContainer != null) {
    if (!AndroidUtilities.isTablet()) {
      FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)dropDownContainer.getLayoutParams();
      layoutParams.topMargin=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
      dropDownContainer.setLayoutParams(layoutParams);
    }
    if (!AndroidUtilities.isTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      dropDown.setTextSize(18);
    }
 else {
      dropDown.setTextSize(20);
    }
  }
}
