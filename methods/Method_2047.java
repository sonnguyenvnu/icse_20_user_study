/** 
 * Utility method which adds optional configuration to ImageRequest
 * @param imageRequestBuilder The Builder for ImageRequest
 * @param config              The Config
 */
public static void addOptionalFeatures(ImageRequestBuilder imageRequestBuilder,Config config){
  if (config.usePostprocessor) {
    final Postprocessor postprocessor;
switch (config.postprocessorType) {
case "use_slow_postprocessor":
      postprocessor=DelayPostprocessor.getMediumPostprocessor();
    break;
case "use_fast_postprocessor":
  postprocessor=DelayPostprocessor.getFastPostprocessor();
break;
default :
postprocessor=DelayPostprocessor.getMediumPostprocessor();
}
imageRequestBuilder.setPostprocessor(postprocessor);
}
if (config.rotateUsingMetaData) {
imageRequestBuilder.setRotationOptions(RotationOptions.autoRotateAtRenderTime());
}
 else {
imageRequestBuilder.setRotationOptions(RotationOptions.forceRotation(config.forcedRotationAngle));
}
}
