/** 
 * Offset the bounds of all child views by <code>dx</code> pixels. Useful for implementing simple scrolling in  {@link LayoutManager LayoutManagers}.
 * @param dx Horizontal pixel offset to apply to the bounds of all child views
 */
public void offsetChildrenHorizontal(@Px int dx){
  final int childCount=mChildHelper.getChildCount();
  for (int i=0; i < childCount; i++) {
    mChildHelper.getChildAt(i).offsetLeftAndRight(dx);
  }
}
