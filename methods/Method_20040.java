/** 
 * Generates a list of acceptable preview sizes. Preview sizes are not acceptable if there is not a corresponding picture size of the same aspect ratio. If there is a corresponding picture size of the same aspect ratio, the picture size is paired up with the preview size. <p>This is necessary because even if we don't use still pictures, the still picture size must be set to a size that is the same aspect ratio as the preview size we choose. Otherwise, the preview images may be distorted on some devices.
 */
private static List<SizePair> generateValidPreviewSizeList(Camera camera){
  Camera.Parameters parameters=camera.getParameters();
  List<Camera.Size> supportedPreviewSizes=parameters.getSupportedPreviewSizes();
  List<Camera.Size> supportedPictureSizes=parameters.getSupportedPictureSizes();
  List<SizePair> validPreviewSizes=new ArrayList<>();
  for (  android.hardware.Camera.Size previewSize : supportedPreviewSizes) {
    float previewAspectRatio=(float)previewSize.width / (float)previewSize.height;
    for (    android.hardware.Camera.Size pictureSize : supportedPictureSizes) {
      float pictureAspectRatio=(float)pictureSize.width / (float)pictureSize.height;
      if (Math.abs(previewAspectRatio - pictureAspectRatio) < ASPECT_RATIO_TOLERANCE) {
        validPreviewSizes.add(new SizePair(previewSize,pictureSize));
        break;
      }
    }
  }
  if (validPreviewSizes.size() == 0) {
    Log.w(TAG,"No preview sizes have a corresponding same-aspect-ratio picture size");
    for (    android.hardware.Camera.Size previewSize : supportedPreviewSizes) {
      validPreviewSizes.add(new SizePair(previewSize,null));
    }
  }
  return validPreviewSizes;
}
