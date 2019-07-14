protected void setInPreviewMode(boolean value){
  inPreviewMode=value;
  if (actionBar != null) {
    if (inPreviewMode) {
      actionBar.setOccupyStatusBar(false);
    }
 else {
      actionBar.setOccupyStatusBar(Build.VERSION.SDK_INT >= 21);
    }
  }
}
