protected void drawShape(Shape s){
  if (fillGradient) {
    g2.setPaint(fillGradientObject);
    g2.fill(s);
  }
 else   if (fill) {
    g2.setColor(fillColorObject);
    g2.fill(s);
  }
  if (strokeGradient) {
    g2.setPaint(strokeGradientObject);
    g2.draw(s);
  }
 else   if (stroke) {
    g2.setColor(strokeColorObject);
    g2.draw(s);
  }
}
