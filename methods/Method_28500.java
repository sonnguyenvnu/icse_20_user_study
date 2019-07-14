private int measureWidth(Paint paint,CharSequence text,int start,int end,boolean includePaddingAfter){
  final int paddingW=dims.getPadding() + dims.getPaddingExtraWidth();
  final int maxWidth=dims.getMaxWidth();
  int w=(int)paint.measureText(text,start,end) + 2 * paddingW;
  if (includePaddingAfter) {
    w+=dims.getPaddingAfter();
  }
  if (w > maxWidth) {
    w=maxWidth;
  }
  return w;
}
