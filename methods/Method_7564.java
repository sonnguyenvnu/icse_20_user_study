public static int getCurrentActionBarHeight(){
  if (AndroidUtilities.isTablet()) {
    return AndroidUtilities.dp(64);
  }
 else   if (AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y) {
    return AndroidUtilities.dp(48);
  }
 else {
    return AndroidUtilities.dp(56);
  }
}
