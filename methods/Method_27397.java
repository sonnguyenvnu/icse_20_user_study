@Override public int getSize(@NonNull Paint paint,CharSequence text,int start,int end,Paint.FontMetricsInt fm){
  return (int)paint.measureText(text,start,end);
}
