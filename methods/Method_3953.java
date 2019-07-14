/** 
 * Requests that the given child of the RecyclerView be positioned onto the screen. This method can be called for both unfocusable and focusable child views. For unfocusable child views, the  {@param focused} parameter passed is null, whereas for a focusable child, this parameterindicates the actual descendant view within this child view that holds the focus.
 * @param child The child view of this RecyclerView that wants to come onto the screen.
 * @param focused The descendant view that actually has the focus if child is focusable, nullotherwise.
 */
private void requestChildOnScreen(@NonNull View child,@Nullable View focused){
  View rectView=(focused != null) ? focused : child;
  mTempRect.set(0,0,rectView.getWidth(),rectView.getHeight());
  final ViewGroup.LayoutParams focusedLayoutParams=rectView.getLayoutParams();
  if (focusedLayoutParams instanceof LayoutParams) {
    final LayoutParams lp=(LayoutParams)focusedLayoutParams;
    if (!lp.mInsetsDirty) {
      final Rect insets=lp.mDecorInsets;
      mTempRect.left-=insets.left;
      mTempRect.right+=insets.right;
      mTempRect.top-=insets.top;
      mTempRect.bottom+=insets.bottom;
    }
  }
  if (focused != null) {
    offsetDescendantRectToMyCoords(focused,mTempRect);
    offsetRectIntoDescendantCoords(child,mTempRect);
  }
  mLayout.requestChildRectangleOnScreen(this,child,mTempRect,!mFirstLayoutComplete,(focused == null));
}
