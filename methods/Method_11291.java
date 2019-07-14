private float getBaseLine(Paint p,float top,float bottom){
  Paint.FontMetrics fontMetrics=p.getFontMetrics();
  int baseline=(int)((bottom + top - fontMetrics.bottom - fontMetrics.top) / 2);
  return baseline;
}
