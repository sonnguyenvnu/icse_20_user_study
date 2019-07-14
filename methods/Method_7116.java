@TargetApi(Build.VERSION_CODES.P) private void loadRoundAvatar(File avatar,Person.Builder personBuilder){
  if (avatar != null) {
    try {
      Bitmap bitmap=ImageDecoder.decodeBitmap(ImageDecoder.createSource(avatar),(decoder,info,src) -> decoder.setPostProcessor((canvas) -> {
        Path path=new Path();
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        int width=canvas.getWidth();
        int height=canvas.getHeight();
        path.addRoundRect(0,0,width,height,width / 2,width / 2,Path.Direction.CW);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawPath(path,paint);
        return PixelFormat.TRANSLUCENT;
      }
));
      IconCompat icon=IconCompat.createWithBitmap(bitmap);
      personBuilder.setIcon(icon);
    }
 catch (    Throwable ignore) {
    }
  }
}
