private static Bitmap createAlphaBackgroundPattern(int size){
  Paint alphaPatternPaint=PaintBuilder.newPaint().build();
  Bitmap bm=Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
  Canvas c=new Canvas(bm);
  int s=Math.round(size / 2f);
  for (int i=0; i < 2; i++) {
    for (int j=0; j < 2; j++) {
      if ((i + j) % 2 == 0) {
        alphaPatternPaint.setColor(0xffffffff);
      }
 else {
        alphaPatternPaint.setColor(0xffd0d0d0);
      }
      c.drawRect(i * s,j * s,(i + 1) * s,(j + 1) * s,alphaPatternPaint);
    }
  }
  return bm;
}
