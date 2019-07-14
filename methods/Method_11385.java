void invalidate(){
  charWidths.clear();
  final Paint.FontMetrics fm=textPaint.getFontMetrics();
  charHeight=fm.bottom - fm.top;
  charBaseline=-fm.top;
}
