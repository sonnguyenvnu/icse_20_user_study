public static Ticker adaptVaultoroLatest(BigDecimal latest){
  return new Ticker.Builder().last(latest).build();
}
