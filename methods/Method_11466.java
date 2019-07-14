/** 
 * Returns height of the spinnerwheel
 * @return the item height
 */
@Override protected int getItemDimension(){
  if (mItemHeight != 0) {
    return mItemHeight;
  }
  if (mItemsLayout != null && mItemsLayout.getChildAt(0) != null) {
    mItemHeight=mItemsLayout.getChildAt(0).getMeasuredHeight();
    return mItemHeight;
  }
  return getBaseDimension() / mVisibleItems;
}
