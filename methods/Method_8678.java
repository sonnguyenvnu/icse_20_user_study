public void updateColors(){
  backgroundInnerPaint.setColor(Theme.getColor(innerKey));
  backgroundPaint.setColor(Theme.getColor(backgroundKey));
  checkPaint.setColor(Theme.getColor(checkKey));
  invalidate();
}
