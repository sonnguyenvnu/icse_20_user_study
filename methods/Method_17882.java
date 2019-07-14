private void unmountView(View view){
  mIsChildDrawingOrderDirty=true;
  if (mInLayout) {
    super.removeViewInLayout(view);
  }
 else {
    super.removeView(view);
  }
}
