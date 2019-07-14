private void fixLayoutInternal(){
  if (selectedMessagesCountTextView == null) {
    return;
  }
  if (!AndroidUtilities.isTablet() && ApplicationLoader.applicationContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
    selectedMessagesCountTextView.setTextSize(18);
  }
 else {
    selectedMessagesCountTextView.setTextSize(20);
  }
}
