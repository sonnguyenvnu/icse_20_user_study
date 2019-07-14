@Override public int getIntrinsicHeight(){
  if (mExifOrientation == ExifInterface.ORIENTATION_TRANSPOSE || mExifOrientation == ExifInterface.ORIENTATION_TRANSVERSE || mRotationAngle % 180 != 0) {
    return super.getIntrinsicWidth();
  }
 else {
    return super.getIntrinsicHeight();
  }
}
