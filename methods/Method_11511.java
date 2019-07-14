/** 
 * Returns the current scroll position of the scrollable view. If this method returns zero or less, it means at the scrollable view is in a position such as the panel should handle scrolling. If the method returns anything above zero, then the panel will let the scrollable view handle the scrolling
 * @param scrollableView the scrollable view
 * @param isSlidingUp whether or not the panel is sliding up or down
 * @return the scroll position
 */
public int getScrollableViewScrollPosition(View scrollableView,boolean isSlidingUp){
  if (scrollableView == null)   return 0;
  if (scrollableView instanceof ScrollView) {
    if (isSlidingUp) {
      return scrollableView.getScrollY();
    }
 else {
      ScrollView sv=((ScrollView)scrollableView);
      View child=sv.getChildAt(0);
      return (child.getBottom() - (sv.getHeight() + sv.getScrollY()));
    }
  }
 else   if (scrollableView instanceof ListView && ((ListView)scrollableView).getChildCount() > 0) {
    ListView lv=((ListView)scrollableView);
    if (lv.getAdapter() == null)     return 0;
    if (isSlidingUp) {
      View firstChild=lv.getChildAt(0);
      return lv.getFirstVisiblePosition() * firstChild.getHeight() - firstChild.getTop();
    }
 else {
      View lastChild=lv.getChildAt(lv.getChildCount() - 1);
      return (lv.getAdapter().getCount() - lv.getLastVisiblePosition() - 1) * lastChild.getHeight() + lastChild.getBottom() - lv.getBottom();
    }
  }
 else   if (scrollableView instanceof RecyclerView && ((RecyclerView)scrollableView).getChildCount() > 0) {
    RecyclerView rv=((RecyclerView)scrollableView);
    RecyclerView.LayoutManager lm=rv.getLayoutManager();
    if (rv.getAdapter() == null)     return 0;
    if (isSlidingUp) {
      View firstChild=rv.getChildAt(0);
      return rv.getChildLayoutPosition(firstChild) * lm.getDecoratedMeasuredHeight(firstChild) - lm.getDecoratedTop(firstChild);
    }
 else {
      View lastChild=rv.getChildAt(rv.getChildCount() - 1);
      return (rv.getAdapter().getItemCount() - 1) * lm.getDecoratedMeasuredHeight(lastChild) + lm.getDecoratedBottom(lastChild) - rv.getBottom();
    }
  }
 else {
    return 0;
  }
}
