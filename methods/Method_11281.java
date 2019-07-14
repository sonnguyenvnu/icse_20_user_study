private Bitmap getBitmapTail(){
  Canvas canvas=null;
  if (mBitmapTail == null) {
    mBitmapTail=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);
    canvas=new Canvas(mBitmapTail);
    pathTail.reset();
    rectFCabin.bottom=rectFCabin.bottom - RxImageTool.dip2px(5);
    pathTail.moveTo(rectFCabin.centerX(),rectFCabin.bottom + rectFCabin.width() / 2);
    pathTail.lineTo(rectFCabin.centerX() + rectFCabin.width() * 1.5f,rectFCabin.bottom + rectFCabin.width() * 1.5f);
    pathTail.lineTo(rectFCabin.centerX() + rectFCabin.width() * 1.5f,rectFCabin.bottom + rectFCabin.width() * 2f);
    pathTail.lineTo(rectFCabin.centerX(),rectFCabin.bottom + rectFCabin.width() * 1.5f);
    pathTail.lineTo(rectFCabin.centerX() - rectFCabin.width() * 1.5f,rectFCabin.bottom + rectFCabin.width() * 2f);
    pathTail.lineTo(rectFCabin.centerX() - rectFCabin.width() * 1.5f,rectFCabin.bottom + rectFCabin.width() * 1.5f);
    pathTail.close();
    canvas.drawPath(pathTail,mPaint);
    pathTail.reset();
    pathTail.moveTo(rectFCabin.centerX() - rectFCabin.width() / 2 * 0.1f,rectFCabin.bottom + rectFCabin.width() * 1.5f);
    pathTail.quadTo(rectFCabin.centerX(),rectFCabin.bottom + rectFCabin.width() * 3f,rectFCabin.centerX() + rectFCabin.width() / 2 * 0.1f,rectFCabin.bottom + rectFCabin.width() * 1.5f);
    pathTail.close();
    mPaint.setColor(Color.WHITE);
    mPaint.setAlpha(150);
    canvas.drawPath(pathTail,mPaint);
  }
  return mBitmapTail;
}
