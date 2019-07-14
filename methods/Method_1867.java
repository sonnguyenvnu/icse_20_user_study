/** 
 * Compute the transformation matrix needed to rotate the image. If no transformation is needed, it returns null.
 * @param encodedImage The {@link EncodedImage} used when computing the matrix.
 * @param rotationOptions The {@link RotationOptions} used when computing the matrix
 * @return The transformation matrix, or null if no transformation is required.
 */
@Nullable public static Matrix getTransformationMatrix(final EncodedImage encodedImage,final RotationOptions rotationOptions){
  Matrix transformationMatrix=null;
  if (JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS.contains(encodedImage.getExifOrientation())) {
    final int exifOrientation=getForceRotatedInvertedExifOrientation(rotationOptions,encodedImage);
    transformationMatrix=getTransformationMatrixFromInvertedExif(exifOrientation);
  }
 else {
    final int rotationAngle=getRotationAngle(rotationOptions,encodedImage);
    if (rotationAngle != 0) {
      transformationMatrix=new Matrix();
      transformationMatrix.setRotate(rotationAngle);
    }
  }
  return transformationMatrix;
}
