private Bitmap getBitmapCabin(){
  Canvas canvas=null;
  if (mBitmapCabin == null) {
    mBitmapCabin=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);
    canvas=new Canvas(mBitmapCabin);
    mPaint.setColor(Color.WHITE);
    rectFCabin.top=rectFCabin.top - RxImageTool.dip2px(10);
    rectFCabin.bottom=rectFCabin.bottom + RxImageTool.dip2px(5);
    canvas.drawRoundRect(rectFCabin,getMeasuredWidth() / 8f / 2f,getMeasuredWidth() / 8f / 2f,mPaint);
  }
  return mBitmapCabin;
}
