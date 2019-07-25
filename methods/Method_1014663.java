@Override public void paint(Graphics2D g){
  if (!chart.getStyler().isLegendVisible()) {
    return;
  }
  if (chart.getSeriesMap().isEmpty()) {
    return;
  }
  if (chart.getPlot().getBounds().getWidth() < 30) {
    return;
  }
  if (chart.getStyler().getLegendLayout() == Styler.LegendLayout.Vertical) {
    bounds=getBoundsHintVertical();
  }
 else {
    bounds=getBoundsHintHorizontal();
  }
  double height=bounds.getHeight();
switch (chart.getStyler().getLegendPosition()) {
case OutsideE:
    xOffset=chart.getWidth() - bounds.getWidth() - LEGEND_MARGIN;
  yOffset=chart.getPlot().getBounds().getY() + (chart.getPlot().getBounds().getHeight() - bounds.getHeight()) / 2.0;
break;
case InsideNW:
xOffset=chart.getPlot().getBounds().getX() + LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + LEGEND_MARGIN;
break;
case InsideNE:
xOffset=chart.getPlot().getBounds().getX() + chart.getPlot().getBounds().getWidth() - bounds.getWidth() - LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + LEGEND_MARGIN;
break;
case InsideSE:
xOffset=chart.getPlot().getBounds().getX() + chart.getPlot().getBounds().getWidth() - bounds.getWidth() - LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + chart.getPlot().getBounds().getHeight() - bounds.getHeight() - LEGEND_MARGIN;
break;
case InsideSW:
xOffset=chart.getPlot().getBounds().getX() + LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + chart.getPlot().getBounds().getHeight() - bounds.getHeight() - LEGEND_MARGIN;
break;
case InsideN:
xOffset=chart.getPlot().getBounds().getX() + (chart.getPlot().getBounds().getWidth() - bounds.getWidth()) / 2 + LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + LEGEND_MARGIN;
break;
case InsideS:
xOffset=chart.getPlot().getBounds().getX() + (chart.getPlot().getBounds().getWidth() - bounds.getWidth()) / 2 + LEGEND_MARGIN;
yOffset=chart.getPlot().getBounds().getY() + chart.getPlot().getBounds().getHeight() - bounds.getHeight() - LEGEND_MARGIN;
break;
case OutsideS:
xOffset=chart.getPlot().getBounds().getX() + (chart.getPlot().getBounds().getWidth() - bounds.getWidth()) / 2.0;
yOffset=chart.getHeight() - bounds.getHeight() - LEGEND_MARGIN;
break;
default :
break;
}
Shape rect=new Rectangle2D.Double(xOffset,yOffset,bounds.getWidth(),height);
g.setColor(chart.getStyler().getLegendBackgroundColor());
g.fill(rect);
g.setStroke(SOLID_STROKE);
g.setColor(chart.getStyler().getLegendBorderColor());
g.draw(rect);
doPaint(g);
}
