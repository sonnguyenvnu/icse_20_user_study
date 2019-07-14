/** 
 * Authenticated resource that lets you purchase Bitcoin using the primary bank account that is linked to your account. (You must link and verify your bank account through the website before this API call will work). The underlying optional parameter agree_btc_amount_varies is set to false. Use  {@link #buyAndAgreeBTCAmountVaries} to have it set to true.
 * @param quantity The quantity of Bitcoin you would like to buy.
 * @return The {@code CoinbaseTransfer} representing the buy.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/buys/create.html">coinbase.com/api/doc/1.0/buys/create.html</a>
 */
public CoinbaseTransfer buy(BigDecimal quantity) throws IOException {
  final CoinbaseTransfer buyTransfer=coinbase.buy(quantity.toPlainString(),false,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return handleResponse(buyTransfer);
}
