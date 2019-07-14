/** 
 * If measure is called with measure specs that cannot be used to measure the recyclerview, the size of one of an item will be used to determine how to measure instead.
 * @return a size value that can be used to measure the dimension of the recycler that has unknownsize, which is width for vertical scrolling recyclers or height for horizontal scrolling recyclers.
 */
private int getSizeForMeasuring(){
  if (mSizeForMeasure == null) {
    return UNSET;
  }
  return mLayoutInfo.getScrollDirection() == OrientationHelper.HORIZONTAL ? mSizeForMeasure.height : mSizeForMeasure.width;
}
