/** 
 * Convert screen to source x coordinate.
 */
private float viewToSourceX(float vx){
  if (vTranslate == null) {
    return Float.NaN;
  }
  return (vx - vTranslate.x) / scale;
}
