public Bitmap getBitmapFuselage(float rectFCabinWidth){
  Canvas canvas=null;
  int w=getMeasuredWidth();
  int h=getMeasuredHeight();
  if (mBitmapFuselage == null) {
    mBitmapFuselage=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
    canvas=new Canvas(mBitmapFuselage);
    pathFuselage.moveTo(w / 2f - rectFCabinWidth / 2f - RxImageTool.dip2px(2),rectFCabin.top + rectFCabinWidth / 2f);
    pathFuselage.cubicTo(w / 2f - rectFCabinWidth / 4f,rectFCabin.top - rectFCabinWidth * 1.2f,w / 2f + rectFCabinWidth / 4f,rectFCabin.top - rectFCabinWidth * 1.2f,w / 2f + rectFCabinWidth / 2f + RxImageTool.dip2px(2),rectFCabin.top + rectFCabinWidth / 2f);
    rectFCabin.top=rectFCabin.top + RxImageTool.dip2px(10);
    pathFuselage.lineTo(w / 2f + rectFCabinWidth / 2f + RxImageTool.dip2px(2),rectFCabin.top + rectFCabin.height() / 3f);
    pathFuselage.lineTo(w,rectFCabin.top + rectFCabin.height() * 0.55f);
    pathFuselage.lineTo(w,rectFCabin.top + rectFCabin.height() * 0.55f + rectFCabin.width() * 0.8f);
    pathFuselage.lineTo(rectFCabin.right + rectFCabin.width() / 2 * 1.5f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathFuselage.lineTo(w / 2f + rectFCabinWidth / 2f + RxImageTool.dip2px(2),rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathFuselage.lineTo(w / 2f + rectFCabinWidth / 2f + RxImageTool.dip2px(2),rectFCabin.bottom - rectFCabinWidth / 2f);
    pathFuselage.cubicTo(w / 2f + rectFCabinWidth / 4f,rectFCabin.bottom + rectFCabinWidth * 2.5f,w / 2f - rectFCabinWidth / 4f,rectFCabin.bottom + rectFCabinWidth * 2.5f,w / 2f - rectFCabinWidth / 2f - RxImageTool.dip2px(2),rectFCabin.bottom - rectFCabinWidth / 2f);
    pathFuselage.lineTo(w / 2f - rectFCabinWidth / 2f - RxImageTool.dip2px(2),rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathFuselage.lineTo(rectFCabin.left - rectFCabin.width() / 2 * 1.5f,rectFCabin.top + rectFCabin.height() / 2f + rectFCabin.height() / 6f / 2f);
    pathFuselage.lineTo(0,rectFCabin.top + rectFCabin.height() * 0.55f + rectFCabin.width() * 0.8f);
    pathFuselage.lineTo(0,rectFCabin.top + rectFCabin.height() * 0.55f);
    pathFuselage.lineTo(w / 2f - rectFCabinWidth / 2f - RxImageTool.dip2px(2),rectFCabin.top + rectFCabin.height() / 3f);
    pathFuselage.close();
    mPaint.setColor(Color.WHITE);
    mPaint.setAlpha(150);
    canvas.drawPath(pathFuselage,mPaint);
  }
  return mBitmapFuselage;
}
