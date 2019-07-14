@Override public void updateMeasureState(TextPaint p){
  if (textPaint != null) {
    p.setColor(textPaint.getColor());
    p.setTypeface(textPaint.getTypeface());
    p.setFlags(textPaint.getFlags());
    p.setTextSize(textPaint.getTextSize());
    p.baselineShift=textPaint.baselineShift;
    p.bgColor=textPaint.bgColor;
  }
}
