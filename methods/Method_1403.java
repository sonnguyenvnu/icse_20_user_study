private int getOffsetAboveBaseline(Paint.FontMetricsInt fm){
switch (mAlignment) {
case ALIGN_BOTTOM:
    return fm.descent - mHeight;
case ALIGN_CENTER:
  int textHeight=fm.descent - fm.ascent;
int offset=(textHeight - mHeight) / 2;
return fm.ascent + offset;
case ALIGN_BASELINE:
default :
return -mHeight;
}
}
