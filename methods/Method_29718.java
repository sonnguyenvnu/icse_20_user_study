public byte[] getJpeg(){
  JpegTransformer jpegTransformer=new JpegTransformer(picture);
  int width=jpegTransformer.getWidth();
  int height=jpegTransformer.getHeight();
  ExifPostProcessor exifPostProcessor=new ExifPostProcessor(picture);
  exifPostProcessor.apply(jpegTransformer);
  if (facing == FACING_FRONT) {
    jpegTransformer.flipHorizontal();
  }
  if (cropAspectRatio != null) {
    int cropWidth=width;
    int cropHeight=height;
    if (exifPostProcessor.areDimensionsFlipped()) {
      cropWidth=height;
      cropHeight=width;
    }
    new CenterCrop(cropWidth,cropHeight,cropAspectRatio).apply(jpegTransformer);
  }
  return jpegTransformer.getJpeg();
}
