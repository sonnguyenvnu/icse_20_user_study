/** 
 * Returns height of spinnerwheel item
 * @return the item width
 */
@Override protected int getItemDimension(){
  if (itemWidth != 0) {
    return itemWidth;
  }
  if (mItemsLayout != null && mItemsLayout.getChildAt(0) != null) {
    itemWidth=mItemsLayout.getChildAt(0).getMeasuredWidth();
    return itemWidth;
  }
  return getBaseDimension() / mVisibleItems;
}
