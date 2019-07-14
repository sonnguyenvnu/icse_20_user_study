private void fixLayoutInternal(int num){
  WindowManager manager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Activity.WINDOW_SERVICE);
  int rotation=manager.getDefaultDisplay().getRotation();
  if (num == 0) {
    if (!AndroidUtilities.isTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      selectedMessagesCountTextView.setTextSize(18);
    }
 else {
      selectedMessagesCountTextView.setTextSize(20);
    }
  }
  if (AndroidUtilities.isTablet()) {
    columnsCount=4;
    mediaPages[num].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),AndroidUtilities.dp(128));
  }
 else {
    if (rotation == Surface.ROTATION_270 || rotation == Surface.ROTATION_90) {
      columnsCount=6;
      mediaPages[num].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),0);
    }
 else {
      columnsCount=4;
      mediaPages[num].emptyTextView.setPadding(AndroidUtilities.dp(40),0,AndroidUtilities.dp(40),AndroidUtilities.dp(128));
    }
  }
  if (num == 0) {
    photoVideoAdapter.notifyDataSetChanged();
  }
}
