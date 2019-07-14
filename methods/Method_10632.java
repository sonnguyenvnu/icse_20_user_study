private Bitmap rotatingImage(Bitmap bitmap){
  if (srcExif == null) {
    return bitmap;
  }
  Matrix matrix=new Matrix();
  int angle=0;
  int orientation=srcExif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
switch (orientation) {
case ExifInterface.ORIENTATION_ROTATE_90:
    angle=90;
  break;
case ExifInterface.ORIENTATION_ROTATE_180:
angle=180;
break;
case ExifInterface.ORIENTATION_ROTATE_270:
angle=270;
break;
default :
break;
}
matrix.postRotate(angle);
return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
}
