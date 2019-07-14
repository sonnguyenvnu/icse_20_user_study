@RequiresApi(api=Build.VERSION_CODES.HONEYCOMB_MR1) public static void xferRoundBitmap(Bitmap output,Bitmap source,boolean enableAntiAliasing){
  Preconditions.checkNotNull(source);
  Preconditions.checkNotNull(output);
  output.setHasAlpha(true);
  Paint circlePaint;
  Paint xfermodePaint;
  if (enableAntiAliasing) {
    circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    xfermodePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
  }
 else {
    circlePaint=new Paint();
    xfermodePaint=new Paint();
  }
  circlePaint.setColor(Color.BLACK);
  xfermodePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  Canvas canvas=new Canvas(output);
  float xCenter=source.getWidth() / 2f;
  float yCenter=source.getHeight() / 2f;
  canvas.drawCircle(xCenter,yCenter,Math.min(xCenter,yCenter),circlePaint);
  canvas.drawBitmap(source,0,0,xfermodePaint);
}
