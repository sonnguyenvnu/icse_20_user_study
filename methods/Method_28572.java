@Override public void updateDrawState(TextPaint tp){
  super.updateDrawState(tp);
  updateMeasureState(tp);
  if (color != -1)   tp.setColor(color);
}
