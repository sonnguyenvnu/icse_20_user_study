private Bitmap makeBitmap(){
  paddingLeft=0;
  Bitmap bp=Bitmap.createBitmap(width,height,Config.ARGB_8888);
  Canvas c=new Canvas(bp);
  code=makeCode();
  c.drawColor(Color.rgb(defaultColor,defaultColor,defaultColor));
  Paint paint=new Paint();
  paint.setTextSize(fontSize);
  for (int i=0; i < code.length(); i++) {
    randomTextStyle(paint);
    randomPadding();
    c.drawText(code.charAt(i) + "",paddingLeft,paddingTop,paint);
  }
  for (int i=0; i < lineNumber; i++) {
    drawLine(c,paint);
  }
  c.save();
  c.restore();
  return bp;
}
