/** 
 * Authenticated resource that generates a new Bitcoin receive address for the user.
 * @return The user’s newly generated and current {@code CoinbaseAddress}.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/accounts/generate_receive_address.html">coinbase.com/api/doc/1.0/accounts/generate_receive_address
   *     .html</a>
 */
public CoinbaseAddress generateCoinbaseReceiveAddress() throws IOException {
  return generateCoinbaseReceiveAddress(null,null);
}
