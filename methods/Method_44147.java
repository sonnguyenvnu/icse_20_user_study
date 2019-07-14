private void updateData() throws IOException {
  BitcoiniumOrderbook bitcoiniumOrderbook=bitcoiniumMarketDataService.getBitcoiniumOrderbook("BTC","BITSTAMP_USD","TEN_PERCENT");
  System.out.println(bitcoiniumOrderbook.toString());
  List<Float> bidsPriceData=getPriceData(bitcoiniumOrderbook.getBids());
  Collections.reverse(bidsPriceData);
  List<Float> bidsVolumeData=getVolumeData(bitcoiniumOrderbook.getBids());
  Collections.reverse(bidsVolumeData);
  xAxisBidData=bidsPriceData;
  yAxisBidData=bidsVolumeData;
  xAxisAskData=getPriceData(bitcoiniumOrderbook.getAsks());
  yAxisAskData=getVolumeData(bitcoiniumOrderbook.getAsks());
}
