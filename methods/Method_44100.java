public static String currencyPairToMarketName(CurrencyPair currencyPair){
  String base=currencyPair.base.getCurrencyCode();
  String counter=currencyPair.counter.getCurrencyCode();
  String marketName=(base + counter).toLowerCase();
  marketName=marketName.replace("bch","bcc");
  return marketName;
}
