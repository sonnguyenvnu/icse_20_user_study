private void saveWindowPosition(Preferences prefs,String windowIdPrefix,WindowPosition windowPosition){
  prefs.putBoolean(windowIdPrefix + WINDOW_IS_FULL_SCREEN_ID,windowPosition.isFullScreen());
  prefs.putInt(windowIdPrefix + WINDOW_WIDTH_ID,windowPosition.getWindowWidth());
  prefs.putInt(windowIdPrefix + WINDOW_HEIGHT_ID,windowPosition.getWindowHeight());
  prefs.putInt(windowIdPrefix + WINDOW_X_ID,windowPosition.getWindowX());
  prefs.putInt(windowIdPrefix + WINDOW_Y_ID,windowPosition.getWindowY());
}
