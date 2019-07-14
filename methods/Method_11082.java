/** 
 * Draws shadows on top and bottom of control
 * @param canvas the canvas for drawing
 */
private void drawShadows(Canvas canvas){
  int height=(int)(1.5 * getItemHeight());
  topShadow.setBounds(0,0,getWidth(),height);
  topShadow.draw(canvas);
  bottomShadow.setBounds(0,getHeight() - height,getWidth(),getHeight());
  bottomShadow.draw(canvas);
}
