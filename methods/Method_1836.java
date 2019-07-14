/** 
 * @return width of the image
 */
@Override public int getWidth(){
  if (mRotationAngle % 180 != 0 || mExifOrientation == ExifInterface.ORIENTATION_TRANSPOSE || mExifOrientation == ExifInterface.ORIENTATION_TRANSVERSE) {
    return getBitmapHeight(mBitmap);
  }
  return getBitmapWidth(mBitmap);
}
