/** 
 * Resize frame for these sketch (canvas) dimensions. 
 */
private Dimension setFrameSize(){
  frame.addNotify();
  currentInsets=frame.getInsets();
  int windowW=Math.max(sketchWidth,MIN_WINDOW_WIDTH) + currentInsets.left + currentInsets.right;
  int windowH=Math.max(sketchHeight,MIN_WINDOW_HEIGHT) + currentInsets.top + currentInsets.bottom;
  frame.setSize(windowW,windowH);
  return new Dimension(windowW,windowH);
}
