private static void getCandles(HitbtcMarketDataServiceRaw hitbtcMarketDataService) throws IOException, ParseException {
  CurrencyPair currencyPair=new CurrencyPair("BTC/USD");
  int limit=10;
  String sort="ASC";
  String period="M15";
  int offset=10;
  DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  LocalDateTime end=LocalDateTime.parse("2019-01-24 00:00",formatter);
  LocalDateTime start=LocalDateTime.parse("2019-01-23 00:00",formatter);
  Date from=Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
  Date till=Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
  System.out.println("Default");
  List<HitbtcCandle> candles=hitbtcMarketDataService.getHitbtcCandles(currencyPair,limit,period);
  printCandles(candles);
  sort="ASC";
  System.out.println("Sorted " + sort);
  candles=hitbtcMarketDataService.getHitbtcCandles(currencyPair,limit,period,sort);
  printCandles(candles);
  sort="DESC";
  System.out.println("Sorted " + sort);
  candles=hitbtcMarketDataService.getHitbtcCandles(currencyPair,limit,period,sort);
  printCandles(candles);
  System.out.println("Filtered from " + from + " to " + till + " and sort " + sort);
  candles=hitbtcMarketDataService.getHitbtcCandles(currencyPair,limit,period,from,till,"ASC");
  printCandles(candles);
  System.out.println("Using offset " + offset + " and sort " + sort);
  candles=hitbtcMarketDataService.getHitbtcCandles(currencyPair,limit,period,offset,sort);
  printCandles(candles);
}
