private int getGroupPhotosWidth(){
  if (!AndroidUtilities.isInMultiwindow && AndroidUtilities.isTablet() && (!AndroidUtilities.isSmallTablet() || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
    int leftWidth=AndroidUtilities.displaySize.x / 100 * 35;
    if (leftWidth < AndroidUtilities.dp(320)) {
      leftWidth=AndroidUtilities.dp(320);
    }
    return AndroidUtilities.displaySize.x - leftWidth;
  }
 else {
    return AndroidUtilities.displaySize.x;
  }
}
