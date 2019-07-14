protected void unbindFromView(View view){
  if (view != mBoundView) {
    return;
  }
  mBoundView=null;
}
