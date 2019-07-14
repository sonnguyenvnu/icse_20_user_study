private void fixLayoutInternal(){
  if (getParentActivity() == null) {
    return;
  }
  WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Activity.WINDOW_SERVICE);
  int rotation=manager.getDefaultDisplay().getRotation();
  columnsCount=2;
  if (!AndroidUtilities.isTablet() && (rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90)) {
    columnsCount=4;
  }
  listAdapter.notifyDataSetChanged();
}
