/** 
 * Set the alpha portion of the color.
 * @param color   the (a)rgb color to set an alpha for.
 * @param alpha   the new alpha value, between 0 and 255.
 */
public static @ColorInt int setAlpha(final int color,@IntRange(from=0,to=255) final int alpha){
  return (color & 0x00FFFFFF) | (alpha << 24);
}
