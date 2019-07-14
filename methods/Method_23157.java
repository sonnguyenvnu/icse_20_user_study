protected void strokeShape(Shape s){
  if (strokeGradient) {
    g2.setPaint(strokeGradientObject);
    g2.draw(s);
  }
 else   if (stroke) {
    g2.setColor(strokeColorObject);
    g2.draw(s);
  }
}
