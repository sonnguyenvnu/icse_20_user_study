/** 
 * Convert source to view x coordinate.
 */
private float sourceToViewX(float sx){
  if (vTranslate == null) {
    return Float.NaN;
  }
  return (sx * scale) + vTranslate.x;
}
