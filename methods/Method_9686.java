private void fixLayoutInternal(){
  if (getParentActivity() == null) {
    return;
  }
  WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Activity.WINDOW_SERVICE);
  int rotation=manager.getDefaultDisplay().getRotation();
  if (AndroidUtilities.isTablet()) {
    columnsCount=3;
  }
 else {
    if (rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90) {
      columnsCount=5;
    }
 else {
      columnsCount=3;
    }
  }
  updateRows();
}
