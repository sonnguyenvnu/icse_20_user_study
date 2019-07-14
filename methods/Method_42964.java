public static List<Ticker> adaptPriceQuantities(List<BinancePriceQuantity> priceQuantities){
  return priceQuantities.stream().map(BinanceAdapters::adaptPriceQuantity).collect(Collectors.toList());
}
