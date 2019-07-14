@Override public int getSize(Paint paint,CharSequence text,int start,int end,Paint.FontMetricsInt fm){
  padding=paint.measureText("t");
  width=(int)(paint.measureText(text,start,end) + padding * 2);
  return width;
}
