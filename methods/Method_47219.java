public void setColor(@ColorInt int color){
  paint.setColor(color);
  paint.setAntiAlias(true);
  invalidate();
}
