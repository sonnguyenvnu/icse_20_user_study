/** 
 * Returns the transformation matrix if the orientation corresponds to one present in  {@link #INVERTED_EXIF_ORIENTATIONS}, else null.
 * @param orientation the exif orientation
 * @return the transformation matrix if inverted orientation, else null.
 */
@Nullable private static Matrix getTransformationMatrixFromInvertedExif(final int orientation){
  Matrix matrix=new Matrix();
switch (orientation) {
case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
    matrix.setScale(-1,1);
  break;
case ExifInterface.ORIENTATION_TRANSVERSE:
matrix.setRotate(-90);
matrix.postScale(-1,1);
break;
case ExifInterface.ORIENTATION_FLIP_VERTICAL:
matrix.setRotate(180);
matrix.postScale(-1,1);
break;
case ExifInterface.ORIENTATION_TRANSPOSE:
matrix.setRotate(90);
matrix.postScale(-1,1);
break;
default :
return null;
}
return matrix;
}
