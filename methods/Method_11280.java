public Bitmap getBitmapArrow(){
  Canvas canvas=null;
  if (mBitmapArrow == null) {
    mBitmapArrow=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);
    canvas=new Canvas(mBitmapArrow);
    pathArrow.reset();
    pathArrow.moveTo(rectFCabin.right + rectFCabin.width() / 2 * 1.2f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathArrow.quadTo(rectFCabin.right + rectFCabin.width() / 2 * 1.3f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 5f,rectFCabin.right + rectFCabin.width() / 2 * 1.4f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathArrow.close();
    canvas.drawPath(pathArrow,mPaint);
    pathArrow.reset();
    pathArrow.moveTo(rectFCabin.left - rectFCabin.width() / 2 * 1.2f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathArrow.quadTo(rectFCabin.left - rectFCabin.width() / 2 * 1.3f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 5f,rectFCabin.left - rectFCabin.width() / 2 * 1.4f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathArrow.close();
    canvas.drawPath(pathArrow,mPaint);
    pathArrow.reset();
    float right1x=getMeasuredWidth();
    float right1y=rectFCabin.top + rectFCabin.height() * 0.55f + rectFCabin.width() * 0.8f;
    float right2x=rectFCabin.right + rectFCabin.width() / 2 * 1.5f;
    float right2y=rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f;
    pathArrow.moveTo((right1x + right2x) / 2f,(right1y + right2y) / 2f);
    pathArrow.quadTo((right1x + right2x) / 2f + rectFCabin.width() / 2 * 0.1f,(right1y + right2y) / 2f + rectFCabin.height() / 60f * 7f,(right1x + right2x) / 2f + rectFCabin.width() / 2 * 0.2f,(right1y + right2y) / 2f);
    canvas.drawPath(pathArrow,mPaint);
    pathArrow.reset();
    float left1x=0;
    float left1y=rectFCabin.top + rectFCabin.height() * 0.55f + rectFCabin.width() * 0.8f;
    float left2x=rectFCabin.left - rectFCabin.width() / 2 * 1.5f;
    float left2y=rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f;
    pathArrow.moveTo((left1x + left2x) / 2f,(left1y + left2y) / 2f);
    pathArrow.quadTo((left1x + left2x) / 2f - rectFCabin.width() / 2 * 0.1f,(left1y + left2y) / 2f + rectFCabin.height() / 60f * 7f,(left1x + left2x) / 2f - rectFCabin.width() / 2 * 0.2f,(left1y + left2y) / 2f);
    mPaint.setColor(Color.WHITE);
    mPaint.setAlpha(150);
    canvas.drawPath(pathArrow,mPaint);
  }
  return mBitmapArrow;
}
