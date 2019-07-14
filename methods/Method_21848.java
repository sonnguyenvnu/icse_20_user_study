@Override public void drawBackground(Canvas canvas,Paint paint,int left,int right,int top,int baseline,int bottom,CharSequence charSequence,int start,int end,int lineNum){
  int oldColor=paint.getColor();
  if (color != 0) {
    paint.setColor(color);
  }
  canvas.drawCircle((left + right) / 2,bottom + radius,radius,paint);
  paint.setColor(oldColor);
}
