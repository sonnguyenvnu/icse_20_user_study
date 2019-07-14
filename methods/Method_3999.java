/** 
 * Offset the bounds of all child views by <code>dy</code> pixels. Useful for implementing simple scrolling in  {@link LayoutManager LayoutManagers}.
 * @param dy Vertical pixel offset to apply to the bounds of all child views
 */
public void offsetChildrenVertical(@Px int dy){
  final int childCount=mChildHelper.getChildCount();
  for (int i=0; i < childCount; i++) {
    mChildHelper.getChildAt(i).offsetTopAndBottom(dy);
  }
}
