/** 
 * Show the PeekView
 * @param activity
 * @param motionEvent
 */
public void show(PeekViewActivity activity,MotionEvent motionEvent){
  PeekView peek;
  if (layout == null) {
    peek=new PeekView(activity,options,layoutRes,callbacks);
  }
 else {
    peek=new PeekView(activity,options,layout,callbacks);
  }
  peek.setOffsetByMotionEvent(motionEvent);
  activity.showPeek(peek,motionEvent.getRawY());
}
