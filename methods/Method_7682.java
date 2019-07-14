private boolean recreateLayoutMaybe(){
  if (wasLayout && getMeasuredHeight() != 0) {
    return createLayout(getMeasuredWidth());
  }
 else {
    requestLayout();
  }
  return true;
}
