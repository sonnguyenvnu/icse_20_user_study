public static void addCustomDrawableFactories(Context context,DraweeConfig.Builder draweeConfigBuilder){
  draweeConfigBuilder.addCustomDrawableFactory(ColorImageExample.createDrawableFactory());
  if (isSvgEnabled(context)) {
    draweeConfigBuilder.addCustomDrawableFactory(new SvgDecoderExample.SvgDrawableFactory());
  }
  if (isKeyframesEnabled()) {
    draweeConfigBuilder.addCustomDrawableFactory(KeyframesDecoderExample.createDrawableFactory());
  }
}
