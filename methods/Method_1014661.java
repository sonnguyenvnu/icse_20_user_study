public void init(XChartPanel<XYChart> chartPanel){
  this.chartPanel=chartPanel;
  chart=chartPanel.getChart();
  chart.addPlotPart(this);
}
