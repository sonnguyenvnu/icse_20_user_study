/** 
 * Authenticated resource which claims a redeemable token for its address and Bitcoin.
 * @param tokenId
 * @return True if the redemption was successful.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/tokens/redeem.html">coinbase.com/api/doc/1.0/tokens/redeem.html</a>
 */
public boolean redeemCoinbaseToken(String tokenId) throws IOException {
  final CoinbaseBaseResponse response=coinbase.redeemToken(tokenId,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return handleResponse(response).isSuccess();
}
