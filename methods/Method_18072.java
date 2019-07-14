private static void drawCornerLine(Canvas canvas,Paint paint,int left,int top,int right,int bottom){
  if (left > right) {
    final int tmp=left;
    left=right;
    right=tmp;
  }
  if (top > bottom) {
    final int tmp=top;
    top=bottom;
    bottom=tmp;
  }
  canvas.drawRect(left,top,right,bottom,paint);
}
