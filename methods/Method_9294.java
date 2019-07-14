@Override public void onMultiWindowModeChanged(boolean isInMultiWindowMode){
  AndroidUtilities.isInMultiwindow=isInMultiWindowMode;
  checkLayout();
}
