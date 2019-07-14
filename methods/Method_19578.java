private Paint buildPaint(int strokeWidth){
  Paint paint=new Paint();
  paint.setColor(Color.BLACK);
  paint.setAntiAlias(true);
  paint.setStyle(Paint.Style.STROKE);
  paint.setStrokeWidth(strokeWidth);
  return paint;
}
