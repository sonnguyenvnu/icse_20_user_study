/** 
 * Pass all touch events to the pager so scrolling works on the edges of the calendar view.
 */
@Override public boolean onTouchEvent(MotionEvent event){
  return pager.dispatchTouchEvent(event);
}
