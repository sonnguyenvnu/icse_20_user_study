public static CurrencyPair adaptSymbol(String symbol){
  if (symbols.isEmpty()) {
    try {
      HitbtcExchange exchange=ExchangeFactory.INSTANCE.createExchange(HitbtcExchange.class);
      symbols=new HitbtcMarketDataServiceRaw(exchange).getHitbtcSymbols().stream().collect(Collectors.toMap(hitbtcSymbol -> hitbtcSymbol.getBaseCurrency() + hitbtcSymbol.getQuoteCurrency(),hitbtcSymbol -> new CurrencyPair(hitbtcSymbol.getBaseCurrency(),hitbtcSymbol.getQuoteCurrency())));
    }
 catch (    Exception ignored) {
    }
  }
  return symbols.containsKey(symbol) ? symbols.get(symbol) : guessSymbol(symbol);
}
