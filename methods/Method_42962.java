public static CurrencyPair adaptSymbol(String symbol){
  int pairLength=symbol.length();
  if (symbol.endsWith("USDT")) {
    return new CurrencyPair(symbol.substring(0,pairLength - 4),"USDT");
  }
 else   if (symbol.endsWith("USDC")) {
    return new CurrencyPair(symbol.substring(0,pairLength - 4),"USDC");
  }
 else   if (symbol.endsWith("TUSD")) {
    return new CurrencyPair(symbol.substring(0,pairLength - 4),"TUSD");
  }
 else   if (symbol.endsWith("USDS")) {
    return new CurrencyPair(symbol.substring(0,pairLength - 4),"USDS");
  }
 else {
    return new CurrencyPair(symbol.substring(0,pairLength - 3),symbol.substring(pairLength - 3));
  }
}
