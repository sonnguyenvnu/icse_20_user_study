/** 
 * Get the factor between the dimensions of the encodedImage (actual image) and the ones of the imageRequest (requested size).
 * @param rotationOptions the rotations options of the request
 * @param resizeOptions the resize options of the request
 * @param encodedImage the encoded image with the actual dimensions
 * @param maxBitmapSize the maximum supported bitmap size (in pixels) when not specified in theencoded image resizeOptions.
 * @return
 */
public static int determineSampleSize(final RotationOptions rotationOptions,@Nullable final ResizeOptions resizeOptions,final EncodedImage encodedImage,final int maxBitmapSize){
  if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
    return DEFAULT_SAMPLE_SIZE;
  }
  float ratio=determineDownsampleRatio(rotationOptions,resizeOptions,encodedImage);
  int sampleSize;
  if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
    sampleSize=ratioToSampleSizeJPEG(ratio);
  }
 else {
    sampleSize=ratioToSampleSize(ratio);
  }
  int maxDimension=Math.max(encodedImage.getHeight(),encodedImage.getWidth());
  final float computedMaxBitmapSize=resizeOptions != null ? resizeOptions.maxBitmapSize : maxBitmapSize;
  while (maxDimension / sampleSize > computedMaxBitmapSize) {
    if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
      sampleSize*=2;
    }
 else {
      sampleSize++;
    }
  }
  return sampleSize;
}
