@Override public void placeWindow(int[] location,int[] editorLocation){
  Dimension window=setFrameSize();
  int contentW=Math.max(sketchWidth,MIN_WINDOW_WIDTH);
  int contentH=Math.max(sketchHeight,MIN_WINDOW_HEIGHT);
  if (sketch.sketchFullScreen()) {
    setFullFrame();
  }
  if (!sketch.sketchFullScreen()) {
    if (location != null) {
      frame.setLocation(location[0],location[1]);
    }
 else     if (editorLocation != null) {
      int locationX=editorLocation[0] - 20;
      int locationY=editorLocation[1];
      if (locationX - window.width > 10) {
        frame.setLocation(locationX - window.width,locationY);
      }
 else {
        locationX=(sketch.displayWidth - window.width) / 2;
        locationY=(sketch.displayHeight - window.height) / 2;
        frame.setLocation(locationX,locationY);
      }
    }
 else {
      setFrameCentered();
    }
    Point frameLoc=frame.getLocation();
    if (frameLoc.y < 0) {
      frame.setLocation(frameLoc.x,30);
    }
  }
  canvas.setBounds((contentW - sketchWidth) / 2,(contentH - sketchHeight) / 2,sketchWidth,sketchHeight);
  setupFrameResizeListener();
}
