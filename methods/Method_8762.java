@Override public void setAlpha(int alpha){
  if (thumbDrawable != null) {
    thumbDrawable.setAlpha(alpha);
  }
  paint.setAlpha(alpha);
  docPaint.setAlpha(alpha);
  namePaint.setAlpha(alpha);
  sizePaint.setAlpha(alpha);
  buttonPaint.setAlpha(alpha);
  percentPaint.setAlpha(alpha);
  openPaint.setAlpha(alpha);
}
