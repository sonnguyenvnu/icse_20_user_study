/** 
 * Returns a possibly darker component for a color.
 * @param i An RGB component for a color (0-255).
 * @return A possibly brighter value for the component.
 */
private static final int possiblyDarker(int i){
  return i-=(int)(i * 0.4f);
}
