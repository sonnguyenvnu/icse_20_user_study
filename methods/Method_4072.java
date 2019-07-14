private void setLayoutStateDirection(int direction){
  mLayoutState.mLayoutDirection=direction;
  mLayoutState.mItemDirection=(mShouldReverseLayout == (direction == LayoutState.LAYOUT_START)) ? LayoutState.ITEM_DIRECTION_TAIL : LayoutState.ITEM_DIRECTION_HEAD;
}
