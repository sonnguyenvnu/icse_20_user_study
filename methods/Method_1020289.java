protected final void repaint(int x,int y,int width,int height){
  graphics.setCanvas(offscreen.getCanvas(),offscreen.getBitmap());
  graphics.reset();
  graphics.setClip(x,y,width,height);
  try {
    paint(graphics,width,height);
  }
 catch (  Throwable t) {
    t.printStackTrace();
  }
  view.postInvalidate();
}
