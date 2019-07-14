public static CurrencyPair adaptSymbol(HitbtcSymbol hitbtcSymbol){
  return new CurrencyPair(hitbtcSymbol.getBaseCurrency(),hitbtcSymbol.getQuoteCurrency());
}
