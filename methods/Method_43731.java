public static List<Ticker> adaptTickers(CoinbeneTicker.Container container){
  long timestamp=container.getTimestamp();
  return container.getTickers().stream().map(coinbeneTicker -> adaptCoinbeneTicker(coinbeneTicker,timestamp)).collect(Collectors.toList());
}
