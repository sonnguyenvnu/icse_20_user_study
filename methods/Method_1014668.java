@Override public void paint(Graphics2D g,int width,int height){
  setWidth(width);
  setHeight(height);
  for (  PieSeries seriesPie : getSeriesMap().values()) {
    PieSeries.PieSeriesRenderStyle seriesType=seriesPie.getChartPieSeriesRenderStyle();
    if (seriesType == null) {
      seriesPie.setChartPieSeriesRenderStyle(getStyler().getDefaultSeriesRenderStyle());
    }
  }
  setSeriesStyles();
  paintBackground(g);
  plot.paint(g);
  chartTitle.paint(g);
  legend.paint(g);
}
