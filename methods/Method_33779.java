/** 
 * ??View??????
 * @return canChildScrollUp
 */
public boolean canChildScrollUp(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
    if (mTargetView instanceof AbsListView) {
      final AbsListView absListView=(AbsListView)mTargetView;
      return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
    }
 else {
      return ViewCompat.canScrollVertically(mTargetView,-1) || mTargetView.getScrollY() > 0;
    }
  }
 else {
    return ViewCompat.canScrollVertically(mTargetView,-1);
  }
}
