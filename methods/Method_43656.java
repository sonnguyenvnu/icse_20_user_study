public static CoinbaseMoney getCoinbaseMoneyFromCents(JsonNode node){
  final String amount=node.path("cents").asText();
  final String currency=node.path("currency_iso").asText();
  final int numDecimals=(currency.equalsIgnoreCase("BTC")) ? 8 : 2;
  return new CoinbaseMoney(currency,new BigDecimal(amount).movePointLeft(numDecimals));
}
