private void createMaskCanvas(int width,int height,int oldw,int oldh){
  boolean sizeChanged=width != oldw || height != oldh;
  boolean isValid=width > 0 && height > 0;
  if (isValid && (maskCanvas == null || sizeChanged)) {
    maskCanvas=new Canvas();
    maskBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    maskCanvas.setBitmap(maskBitmap);
    maskPaint.reset();
    paintMaskCanvas(maskCanvas,maskPaint,width,height);
    drawableCanvas=new Canvas();
    drawableBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    drawableCanvas.setBitmap(drawableBitmap);
    drawablePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    drawablePaint.setColor(paintColor);
    invalidated=true;
  }
}
