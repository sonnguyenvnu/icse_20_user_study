/** 
 * <p>Collects some information from  {@link #mCameraCharacteristics}.</p> <p>This rewrites  {@link #mPreviewSizes},  {@link #mPictureSizes}, and optionally, {@link #mAspectRatio}.</p>
 */
private void collectCameraInfo(){
  StreamConfigurationMap map=mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
  if (map == null) {
    throw new IllegalStateException("Failed to get configuration map: " + mCameraId);
  }
  mPreviewSizes.clear();
  for (  android.util.Size size : map.getOutputSizes(mPreview.getOutputClass())) {
    int width=size.getWidth();
    int height=size.getHeight();
    if (width <= MAX_PREVIEW_WIDTH && height <= MAX_PREVIEW_HEIGHT) {
      mPreviewSizes.add(new Size(width,height));
    }
  }
  mPictureSizes.clear();
  collectPictureSizes(mPictureSizes,map);
  for (  AspectRatio ratio : mPreviewSizes.ratios()) {
    if (!mPictureSizes.ratios().contains(ratio)) {
      mPreviewSizes.remove(ratio);
    }
  }
  if (!mPreviewSizes.ratios().contains(mAspectRatio)) {
    mAspectRatio=mPreviewSizes.ratios().iterator().next();
  }
}
