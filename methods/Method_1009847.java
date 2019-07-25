/** 
 * Determine the space between the first two fingers
 * @param event
 */
private float spacing(MotionEvent event){
  float x=event.getX(0) - event.getX(1);
  float y=event.getY(0) - event.getY(1);
  return (float)Math.sqrt(x * x + y * y);
}
