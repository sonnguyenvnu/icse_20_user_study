@ColorInt public static int blendAlphaComponent(@ColorInt int color,@IntRange(from=0x0,to=0xFF) int alpha){
  return androidx.core.graphics.ColorUtils.setAlphaComponent(color,Color.alpha(color) * alpha / 0xFF);
}
