/** 
 * A factory method to build the appropriate LuminanceSource object based on the format of the preview buffers, as described by Camera.Parameters.
 * @param data A preview frame.
 * @param width The width of the image.
 * @param height The height of the image.
 * @return A PlanarYUVLuminanceSource instance.
 */
public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data,int width,int height){
  Rect rect=getFramingRectInPreview();
  int previewFormat=configManager.getPreviewFormat();
  String previewFormatString=configManager.getPreviewFormatString();
switch (previewFormat) {
case PixelFormat.YCbCr_420_SP:
case PixelFormat.YCbCr_422_SP:
    return new PlanarYUVLuminanceSource(data,width,height,rect.left,rect.top,rect.width(),rect.height());
default :
  if ("yuv420p".equals(previewFormatString)) {
    return new PlanarYUVLuminanceSource(data,width,height,rect.left,rect.top,rect.width(),rect.height());
  }
}
throw new IllegalArgumentException("Unsupported picture format: " + previewFormat + '/' + previewFormatString);
}
