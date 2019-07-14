public void updateColors(){
  innerPaint.setColor(Theme.getColor(innerKey));
  outerPaint.setColor(Theme.getColor(outerKey));
  invalidate();
}
