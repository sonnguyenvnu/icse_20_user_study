private static CurrencyPair buildCurrencyPair(QuoineProduct product){
  return new CurrencyPair(product.getBaseCurrency(),product.getQuotedCurrency());
}
