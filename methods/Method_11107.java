public void setSrcColor(int color){
  paintColor=color;
  setImageDrawable(new ColorDrawable(color));
  if (drawablePaint != null) {
    drawablePaint.setColor(color);
    invalidate();
  }
}
