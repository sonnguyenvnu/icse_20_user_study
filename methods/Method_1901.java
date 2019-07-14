/** 
 * Transcodes an image to match the specified exif orientation and the scale factor.
 * @param inputStream The {@link InputStream} of the image that will be transcoded.
 * @param outputStream The {@link OutputStream} where the newly created image is written to.
 * @param exifOrientation 0, 90, 180 or 270
 * @param scaleNumerator 1 - 16, image will be scaled using scaleNumerator/8 factor
 * @param quality 1 - 100
 */
@VisibleForTesting public static void transcodeJpegWithExifOrientation(final InputStream inputStream,final OutputStream outputStream,final int exifOrientation,final int scaleNumerator,final int quality) throws IOException {
  NativeJpegTranscoderSoLoader.ensure();
  Preconditions.checkArgument(scaleNumerator >= MIN_SCALE_NUMERATOR);
  Preconditions.checkArgument(scaleNumerator <= MAX_SCALE_NUMERATOR);
  Preconditions.checkArgument(quality >= MIN_QUALITY);
  Preconditions.checkArgument(quality <= MAX_QUALITY);
  Preconditions.checkArgument(JpegTranscoderUtils.isExifOrientationAllowed(exifOrientation));
  Preconditions.checkArgument(scaleNumerator != SCALE_DENOMINATOR || exifOrientation != ExifInterface.ORIENTATION_NORMAL,"no transformation requested");
  nativeTranscodeJpegWithExifOrientation(Preconditions.checkNotNull(inputStream),Preconditions.checkNotNull(outputStream),exifOrientation,scaleNumerator,quality);
}
