/** 
 * If both the radii and border width are zero, there is nothing to round.
 */
@VisibleForTesting boolean shouldRound(){
  return (mIsCircle || mRadiiNonZero || mBorderWidth > 0);
}
