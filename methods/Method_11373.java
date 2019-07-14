/** 
 * ?????Path
 */
private void createCaptchaPath(){
  int gap=mRandom.nextInt(mCaptchaWidth / 2);
  gap=mCaptchaWidth / 3;
  mCaptchaX=mRandom.nextInt(Math.abs(mWidth - mCaptchaWidth - gap));
  mCaptchaY=mRandom.nextInt(Math.abs(mHeight - mCaptchaHeight - gap));
  Log.d(TAG,"createCaptchaPath() called mWidth:" + mWidth + ", mHeight:" + mHeight + ", mCaptchaX:" + mCaptchaX + ", mCaptchaY:" + mCaptchaY);
  mCaptchaPath.reset();
  mCaptchaPath.lineTo(0,0);
  mCaptchaPath.moveTo(mCaptchaX,mCaptchaY);
  mCaptchaPath.lineTo(mCaptchaX + gap,mCaptchaY);
  drawPartCircle(new PointF(mCaptchaX + gap,mCaptchaY),new PointF(mCaptchaX + gap * 2,mCaptchaY),mCaptchaPath,mRandom.nextBoolean());
  mCaptchaPath.lineTo(mCaptchaX + mCaptchaWidth,mCaptchaY);
  mCaptchaPath.lineTo(mCaptchaX + mCaptchaWidth,mCaptchaY + gap);
  drawPartCircle(new PointF(mCaptchaX + mCaptchaWidth,mCaptchaY + gap),new PointF(mCaptchaX + mCaptchaWidth,mCaptchaY + gap * 2),mCaptchaPath,mRandom.nextBoolean());
  mCaptchaPath.lineTo(mCaptchaX + mCaptchaWidth,mCaptchaY + mCaptchaHeight);
  mCaptchaPath.lineTo(mCaptchaX + mCaptchaWidth - gap,mCaptchaY + mCaptchaHeight);
  drawPartCircle(new PointF(mCaptchaX + mCaptchaWidth - gap,mCaptchaY + mCaptchaHeight),new PointF(mCaptchaX + mCaptchaWidth - gap * 2,mCaptchaY + mCaptchaHeight),mCaptchaPath,mRandom.nextBoolean());
  mCaptchaPath.lineTo(mCaptchaX,mCaptchaY + mCaptchaHeight);
  mCaptchaPath.lineTo(mCaptchaX,mCaptchaY + mCaptchaHeight - gap);
  drawPartCircle(new PointF(mCaptchaX,mCaptchaY + mCaptchaHeight - gap),new PointF(mCaptchaX,mCaptchaY + mCaptchaHeight - gap * 2),mCaptchaPath,mRandom.nextBoolean());
  mCaptchaPath.close();
}
