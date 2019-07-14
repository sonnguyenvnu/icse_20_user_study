private KrakenSpreads getKrakenSpreads(Currency tradableIdentifier,Currency currency,Long since) throws IOException {
  String krakenCurrencyPair=KrakenUtils.createKrakenCurrencyPair(tradableIdentifier,currency);
  KrakenSpreadsResult spreadsResult=kraken.getSpread(krakenCurrencyPair,since);
  return checkResult(spreadsResult);
}
