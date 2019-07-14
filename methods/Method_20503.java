/** 
 * Convert source to view y coordinate.
 */
private float sourceToViewY(float sy){
  if (vTranslate == null) {
    return Float.NaN;
  }
  return (sy * scale) + vTranslate.y;
}
