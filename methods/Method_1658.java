private EncodedImage buildEncodedImage(PooledByteBuffer imageBytes,ExifInterface exifInterface){
  Pair<Integer,Integer> dimensions=BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(imageBytes));
  int rotationAngle=getRotationAngle(exifInterface);
  int width=dimensions != null ? dimensions.first : EncodedImage.UNKNOWN_WIDTH;
  int height=dimensions != null ? dimensions.second : EncodedImage.UNKNOWN_HEIGHT;
  EncodedImage encodedImage;
  CloseableReference<PooledByteBuffer> closeableByteBuffer=CloseableReference.of(imageBytes);
  try {
    encodedImage=new EncodedImage(closeableByteBuffer);
  }
  finally {
    CloseableReference.closeSafely(closeableByteBuffer);
  }
  encodedImage.setImageFormat(DefaultImageFormats.JPEG);
  encodedImage.setRotationAngle(rotationAngle);
  encodedImage.setWidth(width);
  encodedImage.setHeight(height);
  return encodedImage;
}
