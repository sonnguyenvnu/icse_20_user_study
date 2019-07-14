@Override public void onGestureUpdate(MultiPointerGestureDetector detector){
  if (mListener != null) {
    mListener.onGestureUpdate(this);
  }
}
