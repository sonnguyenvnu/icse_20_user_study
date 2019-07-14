private int calculateScrollDirectionForPosition(int position){
  if (getChildCount() == 0) {
    return mShouldReverseLayout ? LayoutState.LAYOUT_END : LayoutState.LAYOUT_START;
  }
  final int firstChildPos=getFirstChildPosition();
  return position < firstChildPos != mShouldReverseLayout ? LayoutState.LAYOUT_START : LayoutState.LAYOUT_END;
}
