@TargetApi(21) private static boolean areSizeAndRateSupportedV21(VideoCapabilities capabilities,int width,int height,double frameRate){
  return frameRate == Format.NO_VALUE || frameRate <= 0 ? capabilities.isSizeSupported(width,height) : capabilities.areSizeAndRateSupported(width,height,frameRate);
}
