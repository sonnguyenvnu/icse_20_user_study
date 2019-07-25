@Override public void paint(Graphics2D g){
  Rectangle2D bounds=getBounds();
  Shape rect=new Rectangle2D.Double(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
  g.setColor(styler.getPlotBackgroundColor());
  g.fill(rect);
  if (styler.isPlotBorderVisible()) {
    g.setColor(styler.getPlotBorderColor());
    g.draw(rect);
  }
}
