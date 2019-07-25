public void init(XChartPanel<XYChart> chartPanel){
  this.chartPanel=chartPanel;
  chart=chartPanel.getChart();
  if (fontColor == null) {
    fontColor=chart.getStyler().getChartFontColor();
  }
  if (textFont == null) {
    textFont=chart.getStyler().getLegendFont();
  }
  chart.addPlotPart(this);
}
