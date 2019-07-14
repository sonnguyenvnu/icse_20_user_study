/** 
 * Convert source to screen x coordinate.
 */
private float sourceToViewX(float sx){
  if (vTranslate == null) {
    return Float.NaN;
  }
  return (sx * scale) + vTranslate.x;
}
