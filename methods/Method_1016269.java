/** 
 * Returns color copy.
 * @param color color to copy
 * @return color copy
 */
public static Color copy(final Color color){
  return new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
}
