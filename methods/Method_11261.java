/** 
 * limit center index in bounds.
 * @param center
 * @return
 */
private int safeCenter(int center){
  if (center < mMinSelectableIndex) {
    center=mMinSelectableIndex;
  }
 else   if (center > mMaxSelectableIndex) {
    center=mMaxSelectableIndex;
  }
  return center;
}
