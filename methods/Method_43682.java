/** 
 * Authenticated resource that shows the current user payment methods.
 * @see <a
   *     href="https://developers.coinbase.com/api/v2#list-payment-methods">developers.coinbase.com/api/v2?shell#list-payment-methods</a>
 */
public List<CoinbasePaymentMethod> getCoinbasePaymentMethods() throws IOException {
  String apiKey=exchange.getExchangeSpecification().getApiKey();
  BigDecimal timestamp=coinbase.getTime(Coinbase.CB_VERSION_VALUE).getData().getEpoch();
  return coinbase.getPaymentMethods(Coinbase.CB_VERSION_VALUE,apiKey,signatureCreator2,timestamp).getData();
}
