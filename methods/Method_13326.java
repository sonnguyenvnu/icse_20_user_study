/** 
 * Returns a possibly brighter component for a color.
 * @param i An RGB component for a color (0-255).
 * @return A possibly brighter value for the component.
 */
private static final int possiblyBrighter(int i){
  if (i < 255) {
    i+=(int)((255 - i) * 0.6f);
  }
  return i;
}
