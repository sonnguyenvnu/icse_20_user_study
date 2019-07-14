@Override public void placeWindow(int[] location,int[] editorLocation){
  if (sketch.sketchFullScreen()) {
    PApplet.hideMenuBar();
    return;
  }
  int wide=sketch.width;
  if (location != null) {
    stage.setX(location[0]);
    stage.setY(location[1]);
  }
 else   if (editorLocation != null) {
    int locationX=editorLocation[0] - 20;
    int locationY=editorLocation[1];
    if (locationX - wide > 10) {
      stage.setX(locationX - wide);
      stage.setY(locationY);
    }
 else {
      stage.centerOnScreen();
    }
  }
 else {
    stage.centerOnScreen();
  }
}
