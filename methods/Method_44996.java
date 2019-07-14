private void adjustWindowPositionBySavedState(){
  WindowPosition windowPosition=ConfigSaver.getLoadedInstance().getFindWindowPosition();
  if (windowPosition.isSavedWindowPositionValid()) {
    this.setLocation(windowPosition.getWindowX(),windowPosition.getWindowY());
  }
}
