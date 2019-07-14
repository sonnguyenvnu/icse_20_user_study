/** 
 * Check whether a color is dark.
 * @param color   the argb color to check.
 */
public static boolean isDark(@ColorInt final int color){
  return !isLight(color);
}
