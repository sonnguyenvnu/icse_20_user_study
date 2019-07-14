private static Point getCameraResolution(Camera.Parameters parameters,Point screenResolution){
  String previewSizeValueString=parameters.get("preview-size-values");
  if (previewSizeValueString == null) {
    previewSizeValueString=parameters.get("preview-size-value");
  }
  Point cameraResolution=null;
  if (previewSizeValueString != null) {
    Log.d(TAG,"preview-size-values parameter: " + previewSizeValueString);
    cameraResolution=findBestPreviewSizeValue(previewSizeValueString,screenResolution);
  }
  if (cameraResolution == null) {
    cameraResolution=new Point((screenResolution.x >> 3) << 3,(screenResolution.y >> 3) << 3);
  }
  return cameraResolution;
}
