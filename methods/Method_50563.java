private void drawDrawableBackground(Canvas canvas){
  if (drawableBackgroundShader == null) {
    if (!(drawable.getIntrinsicHeight() > 0) || !(drawable.getIntrinsicWidth() > 0)) {
      drawable.setBounds(0,0,width,height);
    }
    Bitmap bitmap=STVUtils.drawableToBitmap(drawable,width,height);
    bitmap=computeSuitedBitmapSize(bitmap);
    drawableBackgroundShader=new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
  }
  Shader shader=paint.getShader();
  int color=paint.getColor();
  paint.setColor(Color.WHITE);
  paint.setShader(drawableBackgroundShader);
  canvas.drawPath(solidPath,paint);
  paint.setShader(shader);
  paint.setColor(color);
}
