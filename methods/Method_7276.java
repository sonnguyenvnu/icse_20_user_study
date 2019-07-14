private Icon createRoundBitmap(File path){
  try {
    Bitmap bitmap=BitmapFactory.decodeFile(path.toString());
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
      return Icon.createWithBitmap(result);
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  return null;
}
