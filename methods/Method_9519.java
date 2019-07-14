private int getContainerViewHeight(int mode){
  int height=AndroidUtilities.displaySize.y;
  if (mode == 0 && Build.VERSION.SDK_INT >= 21) {
    height+=AndroidUtilities.statusBarHeight;
  }
  if (mode == 1) {
    height-=AndroidUtilities.dp(48 + 32 + 64);
  }
 else   if (mode == 2) {
    height-=AndroidUtilities.dp(154 + 60);
  }
 else   if (mode == 3) {
    height-=AndroidUtilities.dp(48) + ActionBar.getCurrentActionBarHeight();
  }
  return height;
}
