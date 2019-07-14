/** 
 * Convert screen to source y coordinate.
 */
private float viewToSourceY(float vy){
  if (vTranslate == null) {
    return Float.NaN;
  }
  return (vy - vTranslate.y) / scale;
}
