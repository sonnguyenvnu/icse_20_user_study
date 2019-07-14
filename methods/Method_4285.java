/** 
 * Returns whether the previous pitch period estimate is a better approximation, which can occur at the abrupt end of voiced words.
 */
private boolean previousPeriodBetter(int minDiff,int maxDiff){
  if (minDiff == 0 || prevPeriod == 0) {
    return false;
  }
  if (maxDiff > minDiff * 3) {
    return false;
  }
  if (minDiff * 2 <= prevMinDiff * 3) {
    return false;
  }
  return true;
}
