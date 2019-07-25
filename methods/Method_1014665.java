public void init(XChartPanel<XYChart> chartPanel){
  this.chartPanel=chartPanel;
  chart=chartPanel.getChart();
  chartPanel.addMouseListener(this);
  chartPanel.addMouseMotionListener(this);
  chart.addPlotPart(this);
  resetButton.init(chartPanel);
  resetButton.setVisible(false);
  resetButton.addActionListener(this);
}
