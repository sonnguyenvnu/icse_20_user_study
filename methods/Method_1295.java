protected void unbindFromDrawable(Drawable drawable){
  if (drawable != mBoundDrawable) {
    return;
  }
  mBoundDrawable=null;
}
