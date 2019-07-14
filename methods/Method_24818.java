protected void stopTweakMode(){
  tweakMode=false;
  if (colorSelector != null) {
    colorSelector.hide();
    WindowEvent windowEvent=new WindowEvent(colorSelector.frame,WindowEvent.WINDOW_CLOSING);
    colorSelector.frame.dispatchEvent(windowEvent);
  }
  setCursor(new Cursor(Cursor.TEXT_CURSOR));
  repaint();
}
