@Override public boolean getClipChildren(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
    return mClipChildren;
  }
 else {
    return super.getClipChildren();
  }
}
