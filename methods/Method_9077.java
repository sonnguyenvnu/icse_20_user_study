public void setOverrideColor(int override){
  if (overrideColorProgress == override) {
    return;
  }
  if (overlayBitmap == null) {
    try {
      overlayBitmap=new Bitmap[2];
      overlayCanvas=new Canvas[2];
      for (int a=0; a < 2; a++) {
        overlayBitmap[a]=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);
        overlayCanvas[a]=new Canvas(overlayBitmap[a]);
      }
      overlayMaskBitmap=Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),Bitmap.Config.ARGB_8888);
      overlayMaskCanvas=new Canvas(overlayMaskBitmap);
      overlayEraserPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
      overlayEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
      overlayMaskPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
      overlayMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
      bitmapsCreated=true;
    }
 catch (    Throwable e) {
      return;
    }
  }
  if (!bitmapsCreated) {
    return;
  }
  overrideColorProgress=override;
  overlayCx=0;
  overlayCy=0;
  overlayRad=0;
  invalidate();
}
