/** 
 * Get source width taking rotation into account.
 */
@SuppressWarnings("SuspiciousNameCombination") private int sWidth(){
  int rotation=getRequiredRotation();
  if (rotation == 90 || rotation == 270) {
    return sHeight;
  }
 else {
    return sWidth;
  }
}
