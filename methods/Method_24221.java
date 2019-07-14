@Override protected int getTextWidth(Object font,char[] buffer,int start,int stop){
  int length=stop - start;
  FontMetrics metrics=getFontMetrics((Font)font);
  return metrics.charsWidth(buffer,start,length);
}
