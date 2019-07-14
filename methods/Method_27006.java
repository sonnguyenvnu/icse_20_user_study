public void enable(Display display){
  mDisplay=display;
  mOrientationEventListener.enable();
  dispatchOnDisplayOrientationChanged(DISPLAY_ORIENTATIONS.get(display.getRotation()));
}
