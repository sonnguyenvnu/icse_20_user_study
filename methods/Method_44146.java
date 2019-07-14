public XChartPanel<XYChart> buildPanel() throws IOException {
  System.out.println("fetching data...");
  updateData();
  chart=new XYChartBuilder().width(800).height(400).title("Real-time Bitcoinium Order Book - BITSTAMP_BTC_USD").xAxisTitle("BTC").yAxisTitle("USD").build();
  chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
  chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
  XYSeries series=chart.addSeries(BIDS_SERIES_NAME,xAxisBidData,yAxisBidData);
  series.setMarker(SeriesMarkers.NONE);
  series=chart.addSeries(ASKS_SERIES_NAME,xAxisAskData,yAxisAskData);
  series.setMarker(SeriesMarkers.NONE);
  return new XChartPanel<>(chart);
}
