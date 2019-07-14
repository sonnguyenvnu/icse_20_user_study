@Override public void placeWindow(int[] location,int[] editorLocation){
  if (sketch.sketchFullScreen()) {
    return;
  }
  int x=window.getX() - window.getInsets().getLeftWidth();
  int y=window.getY() - window.getInsets().getTopHeight();
  int w=window.getWidth() + window.getInsets().getTotalWidth();
  int h=window.getHeight() + window.getInsets().getTotalHeight();
  if (location != null) {
    window.setTopLevelPosition(location[0],location[1]);
  }
 else   if (editorLocation != null) {
    int locationX=editorLocation[0] - 20;
    int locationY=editorLocation[1];
    if (locationX - w > 10) {
      window.setTopLevelPosition(locationX - w,locationY);
    }
 else {
      locationX=(sketch.displayWidth - w) / 2;
      locationY=(sketch.displayHeight - h) / 2;
      window.setTopLevelPosition(locationX,locationY);
    }
  }
 else {
    window.setTopLevelPosition(screenRect.x + (screenRect.width - sketchWidth) / 2,screenRect.y + (screenRect.height - sketchHeight) / 2);
  }
  Point frameLoc=new Point(x,y);
  if (frameLoc.y < 0) {
    window.setTopLevelPosition(frameLoc.x,30);
  }
}
