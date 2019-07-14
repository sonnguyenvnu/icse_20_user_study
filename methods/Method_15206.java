/** 
 * @param ev
 * @return
 * @must ?Activity?dispatchTouchEvent?????
 */
public void dispatchTouchEvent(MotionEvent ev){
  gestureDetector.onTouchEvent(ev);
}
