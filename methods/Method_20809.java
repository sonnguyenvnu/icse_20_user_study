/** 
 * Check whether a color is light.
 * @param color   the argb color to check.
 */
public static boolean isLight(@ColorInt final int color){
  return weightedLightness(color) >= KICKSTARTER_LIGHTNESS_THRESHOLD;
}
