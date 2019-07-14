private void updatePointersOnTap(MotionEvent event){
  mPointerCount=0;
  for (int i=0; i < MAX_POINTERS; i++) {
    int index=getPressedPointerIndex(event,i);
    if (index == -1) {
      mId[i]=MotionEvent.INVALID_POINTER_ID;
    }
 else {
      mId[i]=event.getPointerId(index);
      mCurrentX[i]=mStartX[i]=event.getX(index);
      mCurrentY[i]=mStartY[i]=event.getY(index);
      mPointerCount++;
    }
  }
}
