/** 
 * Authenticated resource that generates a new Bitcoin receive address for the user.
 * @param callbackUrl Optional Callback URL to receive instant payment notifications wheneverfunds arrive to this address.
 * @param label Optional text label for the address which can be used to filter against whencalling  {@link #getCoinbaseAddresses}.
 * @return The user’s newly generated and current {@code CoinbaseAddress}.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/accounts/generate_receive_address.html">coinbase.com/api/doc/1.0/accounts/generate_receive_address
   *     .html</a>
 */
public CoinbaseAddress generateCoinbaseReceiveAddress(String callbackUrl,final String label) throws IOException {
  final CoinbaseAddressCallback callbackUrlParam=new CoinbaseAddressCallback(callbackUrl,label);
  final CoinbaseAddress generateReceiveAddress=coinbase.generateReceiveAddress(callbackUrlParam,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return handleResponse(generateReceiveAddress);
}
