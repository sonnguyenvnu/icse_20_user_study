@TargetApi(19) private static boolean isAdaptiveV19(CodecCapabilities capabilities){
  return capabilities.isFeatureSupported(CodecCapabilities.FEATURE_AdaptivePlayback);
}
