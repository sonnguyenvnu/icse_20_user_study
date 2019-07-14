public XChartPanel<XYChart> buildPanel() throws IOException {
  System.out.println("fetching data...");
  BitcoiniumTickerHistory bitcoiniumTickerHistory=bitcoiniumMarketDataService.getBitcoiniumTickerHistory("BTC","BITSTAMP_USD","THREE_HOURS");
  System.out.println(bitcoiniumTickerHistory.toString());
  xAxisData=new ArrayList<>();
  yAxisData=new ArrayList<>();
  for (int i=0; i < bitcoiniumTickerHistory.getCondensedTickers().length; i++) {
    BitcoiniumTicker bitcoiniumTicker=bitcoiniumTickerHistory.getCondensedTickers()[i];
    Date timestamp=new Date(bitcoiniumTicker.getTimestamp());
    float price=bitcoiniumTicker.getLast().floatValue();
    System.out.println(timestamp + ": " + price);
    xAxisData.add(timestamp);
    yAxisData.add(price);
  }
  chart=new XYChartBuilder().width(800).height(600).title("Real-time Bitstamp Price vs. Time").xAxisTitle("Time").yAxisTitle("Price").build();
  chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
  chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
  XYSeries series=chart.addSeries(SERIES_NAME,xAxisData,yAxisData);
  series.setMarker(SeriesMarkers.NONE);
  return new XChartPanel(chart);
}
