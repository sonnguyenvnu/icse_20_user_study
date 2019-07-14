/** 
 * Lighten the argb color by a percentage.
 * @param color   the argb color to lighten.
 * @param percent percentage to lighten by, between 0.0 and 1.0.
 */
public static @ColorInt int lighten(@ColorInt final int color,@FloatRange(from=0.0,to=1.0) final float percent){
  final float[] hsl=new float[3];
  ColorUtils.colorToHSL(color,hsl);
  hsl[2]+=(1.0f - hsl[2]) * percent;
  return (color & 0xFF000000) | (ColorUtils.HSLToColor(hsl) & 0x00FFFFFF);
}
