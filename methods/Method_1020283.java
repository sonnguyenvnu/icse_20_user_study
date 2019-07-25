public final void repaint(int x,int y,int width,int height){
  limitFps();
  postEvent(paintEvent);
}
