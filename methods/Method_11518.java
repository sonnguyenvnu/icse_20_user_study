void updateObscuredViewVisibility(){
  if (getChildCount() == 0) {
    return;
  }
  final int leftBound=getPaddingLeft();
  final int rightBound=getWidth() - getPaddingRight();
  final int topBound=getPaddingTop();
  final int bottomBound=getHeight() - getPaddingBottom();
  final int left;
  final int right;
  final int top;
  final int bottom;
  if (mSlideableView != null && hasOpaqueBackground(mSlideableView)) {
    left=mSlideableView.getLeft();
    right=mSlideableView.getRight();
    top=mSlideableView.getTop();
    bottom=mSlideableView.getBottom();
  }
 else {
    left=right=top=bottom=0;
  }
  View child=getChildAt(0);
  final int clampedChildLeft=Math.max(leftBound,child.getLeft());
  final int clampedChildTop=Math.max(topBound,child.getTop());
  final int clampedChildRight=Math.min(rightBound,child.getRight());
  final int clampedChildBottom=Math.min(bottomBound,child.getBottom());
  final int vis;
  if (clampedChildLeft >= left && clampedChildTop >= top && clampedChildRight <= right && clampedChildBottom <= bottom) {
    vis=INVISIBLE;
  }
 else {
    vis=VISIBLE;
  }
  child.setVisibility(vis);
}
