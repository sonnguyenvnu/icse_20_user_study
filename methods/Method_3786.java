/** 
 * This is called after laying out a row (if vertical) or a column (if horizontal) when the RecyclerView does not have exact measurement specs. <p> Here we try to assign a best guess width or height and re-do the layout to update other views that wanted to MATCH_PARENT in the non-scroll orientation.
 * @param maxSizeInOther The maximum size per span ratio from the measurement of the children.
 * @param currentOtherDirSize The size before this layout chunk. There is no reason to go below.
 */
private void guessMeasurement(float maxSizeInOther,int currentOtherDirSize){
  final int contentSize=Math.round(maxSizeInOther * mSpanCount);
  calculateItemBorders(Math.max(contentSize,currentOtherDirSize));
}
