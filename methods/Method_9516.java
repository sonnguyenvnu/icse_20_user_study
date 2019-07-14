private int getAdditionY(){
  if (currentEditMode == 3) {
    return AndroidUtilities.dp(8) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  }
 else   if (currentEditMode != 0) {
    return AndroidUtilities.dp(14) + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
  }
  return 0;
}
