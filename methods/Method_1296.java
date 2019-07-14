protected void unbindFromPreviousComponent(){
  if (mBoundView != null) {
    unbindFromView(mBoundView);
  }
  if (mBoundDrawable != null) {
    unbindFromDrawable(mBoundDrawable);
  }
}
