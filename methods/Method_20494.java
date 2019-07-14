/** 
 * Get source height taking rotation into account.
 */
@SuppressWarnings("SuspiciousNameCombination") private int sHeight(){
  int rotation=getRequiredRotation();
  if (rotation == 90 || rotation == 270) {
    return sWidth;
  }
 else {
    return sHeight;
  }
}
