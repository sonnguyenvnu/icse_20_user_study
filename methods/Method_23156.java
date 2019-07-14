protected void fillShape(Shape s){
  if (fillGradient) {
    g2.setPaint(fillGradientObject);
    g2.fill(s);
  }
 else   if (fill) {
    g2.setColor(fillColorObject);
    g2.fill(s);
  }
}
