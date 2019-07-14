/** 
 * Set a global preferred bitmap config shared by all view instances and applied to new instances initialised after the call is made. This is a hint only; the bundled  {@link ImageDecoder} and{@link ImageRegionDecoder} classes all respect this (except when they were constructed withan instance-specific config) but custom decoder classes will not.
 * @param preferredBitmapConfig the bitmap configuration to be used by future instances of the view. Pass null to restore the default.
 */
public static void setPreferredBitmapConfig(Bitmap.Config preferredBitmapConfig){
  SubsamplingScaleImageView.preferredBitmapConfig=preferredBitmapConfig;
}
