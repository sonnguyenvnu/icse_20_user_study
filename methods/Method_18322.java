private void maybePerformIncrementalMountOnView(){
  if (mComponentTree == null || !mComponentTree.isIncrementalMountEnabled() || !(getParent() instanceof View)) {
    return;
  }
  int parentWidth=((View)getParent()).getWidth();
  int parentHeight=((View)getParent()).getHeight();
  final int translationX=(int)getTranslationX();
  final int translationY=(int)getTranslationY();
  final int top=getTop() + translationY;
  final int bottom=getBottom() + translationY;
  final int left=getLeft() + translationX;
  final int right=getRight() + translationX;
  if (left >= 0 && top >= 0 && right <= parentWidth && bottom <= parentHeight && mPreviousMountVisibleRectBounds.width() == getWidth() && mPreviousMountVisibleRectBounds.height() == getHeight()) {
    return;
  }
  final Rect rect=new Rect();
  if (!getLocalVisibleRect(rect)) {
    return;
  }
  performIncrementalMount(rect,true);
}
