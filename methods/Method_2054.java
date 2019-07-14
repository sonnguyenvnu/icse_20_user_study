@Nullable public static ImageDecoderConfig createImageDecoderConfig(Context context){
  ImageDecoderConfig.Builder config=ImageDecoderConfig.newBuilder();
  if (isGlobalColorDecoderEnabled(context)) {
    config.addDecodingCapability(ColorImageExample.IMAGE_FORMAT_COLOR,ColorImageExample.createFormatChecker(),ColorImageExample.createDecoder());
  }
  if (isSvgEnabled(context)) {
    config.addDecodingCapability(SvgDecoderExample.SVG_FORMAT,new SvgDecoderExample.SvgFormatChecker(),new SvgDecoderExample.SvgDecoder());
  }
  if (isKeyframesEnabled()) {
    config.addDecodingCapability(KeyframesDecoderExample.IMAGE_FORMAT_KEYFRAMES,KeyframesDecoderExample.createFormatChecker(),KeyframesDecoderExample.createDecoder());
  }
  return config.build();
}
