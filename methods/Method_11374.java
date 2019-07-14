private void craeteMask(){
  mMaskBitmap=getMaskBitmap(((BitmapDrawable)getDrawable()).getBitmap(),mCaptchaPath);
  mMaskShadowBitmap=mMaskBitmap.extractAlpha();
  mDragerOffset=0;
  isDrawMask=true;
}
