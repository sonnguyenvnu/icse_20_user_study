public static CoinbaseMoney getCoinbaseMoneyFromNode(JsonNode node){
  final String amount=node.path("amount").asText();
  final String currency=node.path("currency").asText();
  return new CoinbaseMoney(currency,new BigDecimal(amount));
}
