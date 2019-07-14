/** 
 * Authenticated resource which lets a user complete a money request. Money requests can only be completed by the sender (not the recipient). Remember that the sender in this context is the user who is sending money (not sending the request itself).
 * @param transactionId
 * @return The {@code CoinbaseTransaction} representing the completed {@code CoinbaseSendMoneyRequest}.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/transactions/complete_request.html">coinbase.com/api/doc/1.0/transactions/complete_request.html
   *     </a>
 */
public CoinbaseTransaction completeCoinbaseRequest(String transactionId) throws IOException {
  final CoinbaseTransaction response=coinbase.completeRequest(transactionId,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return handleResponse(response);
}
