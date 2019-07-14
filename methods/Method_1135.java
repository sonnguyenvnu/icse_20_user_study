@Override protected void onBoundsChange(Rect bounds){
  Drawable underlyingDrawable=getCurrent();
  if (mRotationAngle > 0 || (mExifOrientation != ExifInterface.ORIENTATION_UNDEFINED && mExifOrientation != ExifInterface.ORIENTATION_NORMAL)) {
switch (mExifOrientation) {
case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
      mRotationMatrix.setScale(-1,1);
    break;
case ExifInterface.ORIENTATION_FLIP_VERTICAL:
  mRotationMatrix.setScale(1,-1);
break;
case ExifInterface.ORIENTATION_TRANSPOSE:
mRotationMatrix.setRotate(270,bounds.centerX(),bounds.centerY());
mRotationMatrix.postScale(1,-1);
break;
case ExifInterface.ORIENTATION_TRANSVERSE:
mRotationMatrix.setRotate(270,bounds.centerX(),bounds.centerY());
mRotationMatrix.postScale(-1,1);
break;
default :
mRotationMatrix.setRotate(mRotationAngle,bounds.centerX(),bounds.centerY());
break;
}
mTempMatrix.reset();
mRotationMatrix.invert(mTempMatrix);
mTempRectF.set(bounds);
mTempMatrix.mapRect(mTempRectF);
underlyingDrawable.setBounds((int)mTempRectF.left,(int)mTempRectF.top,(int)mTempRectF.right,(int)mTempRectF.bottom);
}
 else {
underlyingDrawable.setBounds(bounds);
}
}
