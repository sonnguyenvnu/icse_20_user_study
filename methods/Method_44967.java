private WindowPosition loadWindowPosition(Preferences prefs,String windowIdPrefix){
  WindowPosition windowPosition=new WindowPosition();
  windowPosition.setFullScreen(prefs.getBoolean(windowIdPrefix + WINDOW_IS_FULL_SCREEN_ID,false));
  windowPosition.setWindowWidth(prefs.getInt(windowIdPrefix + WINDOW_WIDTH_ID,0));
  windowPosition.setWindowHeight(prefs.getInt(windowIdPrefix + WINDOW_HEIGHT_ID,0));
  windowPosition.setWindowX(prefs.getInt(windowIdPrefix + WINDOW_X_ID,0));
  windowPosition.setWindowY(prefs.getInt(windowIdPrefix + WINDOW_Y_ID,0));
  return windowPosition;
}
