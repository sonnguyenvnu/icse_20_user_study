@Override protected void onLongTap(float x,float y){
  if (mGestureListener != null) {
    mGestureListener.onLongTap(this,x,y);
  }
}
