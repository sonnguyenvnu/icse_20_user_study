@Override public final boolean onFling(MotionEvent event1,MotionEvent event2,float velocityX,float velocityY){
  boolean result=false;
  try {
    float deltaX=event2.getX() - event1.getX();
    float deltaY=event2.getY() - event1.getY();
    float absVelocityX=Math.abs(velocityX);
    float absVelocityY=Math.abs(velocityY);
    float absDeltaX=Math.abs(deltaX);
    float absDeltaY=Math.abs(deltaY);
    if (absDeltaX > absDeltaY) {
      if (absDeltaX > mMinSwipeDelta && absVelocityX > mMinSwipeVelocity && absVelocityX < mMaxSwipeVelocity) {
        if (deltaX < 0) {
          onSwipeLeft();
        }
 else {
          onSwipeRight();
        }
      }
      result=true;
    }
 else     if (absDeltaY > mMinSwipeDelta && absVelocityY > mMinSwipeVelocity && absVelocityY < mMaxSwipeVelocity) {
      if (deltaY < 0) {
        onSwipeUp();
      }
 else {
        onSwipeDown();
      }
    }
    result=true;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return result;
}
