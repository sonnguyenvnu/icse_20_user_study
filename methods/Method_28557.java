@SuppressLint("ClickableViewAccessibility") @Override public boolean onTouchEvent(MotionEvent ev){
  boolean returnValue;
  MotionEvent event=MotionEvent.obtain(ev);
  final int action=MotionEventCompat.getActionMasked(event);
  if (action == MotionEvent.ACTION_DOWN) {
    mNestedOffsetY=0;
  }
  int eventY=(int)event.getY();
  event.offsetLocation(0,mNestedOffsetY);
switch (action) {
case MotionEvent.ACTION_MOVE:
    int deltaY=mLastY - eventY;
  if (dispatchNestedPreScroll(0,deltaY,mScrollConsumed,mScrollOffset)) {
    deltaY-=mScrollConsumed[1];
    mLastY=eventY - mScrollOffset[1];
    event.offsetLocation(0,-mScrollOffset[1]);
    mNestedOffsetY+=mScrollOffset[1];
  }
returnValue=super.onTouchEvent(event);
if (dispatchNestedScroll(0,mScrollOffset[1],0,deltaY,mScrollOffset)) {
event.offsetLocation(0,mScrollOffset[1]);
mNestedOffsetY+=mScrollOffset[1];
mLastY-=mScrollOffset[1];
}
break;
case MotionEvent.ACTION_DOWN:
returnValue=super.onTouchEvent(event);
if (firstScroll) {
mLastY=eventY - 5;
firstScroll=false;
}
 else {
mLastY=eventY;
}
startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
break;
default :
returnValue=super.onTouchEvent(event);
stopNestedScroll();
break;
}
return returnValue;
}
