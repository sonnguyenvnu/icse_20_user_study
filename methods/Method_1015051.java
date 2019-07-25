private void setup(){
  if (mBitmap == null) {
    return;
  }
  mBitmapShader=new BitmapShader(mBitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
  mBitmapPaint=new Paint();
  mBitmapPaint.setAntiAlias(true);
  mBitmapPaint.setShader(mBitmapShader);
  mBitmapHeight=mBitmap.getHeight();
  mBitmapWidth=mBitmap.getWidth();
  updateShaderMatrix();
  invalidate();
}
