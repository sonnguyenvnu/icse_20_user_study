/** 
 * @return height of the image
 */
@Override public int getHeight(){
  if (mRotationAngle % 180 != 0 || mExifOrientation == ExifInterface.ORIENTATION_TRANSPOSE || mExifOrientation == ExifInterface.ORIENTATION_TRANSVERSE) {
    return getBitmapWidth(mBitmap);
  }
  return getBitmapHeight(mBitmap);
}
