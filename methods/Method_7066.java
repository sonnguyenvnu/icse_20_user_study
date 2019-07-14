private Bitmap createRoundBitmap(File path){
  try {
    BitmapFactory.Options options=new BitmapFactory.Options();
    options.inSampleSize=2;
    Bitmap bitmap=BitmapFactory.decodeFile(path.toString(),options);
    if (bitmap != null) {
      Bitmap result=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
      result.eraseColor(Color.TRANSPARENT);
      Canvas canvas=new Canvas(result);
      BitmapShader shader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
      if (roundPaint == null) {
        roundPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapRect=new RectF();
      }
      roundPaint.setShader(shader);
      bitmapRect.set(0,0,bitmap.getWidth(),bitmap.getHeight());
      canvas.drawRoundRect(bitmapRect,bitmap.getWidth(),bitmap.getHeight(),roundPaint);
      return result;
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  return null;
}
