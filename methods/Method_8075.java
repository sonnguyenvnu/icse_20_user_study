@Override public boolean performAccessibilityAction(int action,Bundle arguments){
  if (action == R.id.acc_action_open_photo) {
    View parent=(View)getParent();
    parent.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN,getLeft(),getTop() + getHeight() - 1,0));
    parent.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_UP,getLeft(),getTop() + getHeight() - 1,0));
  }
  return super.performAccessibilityAction(action,arguments);
}
