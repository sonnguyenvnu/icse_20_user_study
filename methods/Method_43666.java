/** 
 * Authenticated resource which lets the user request money from a Bitcoin address.
 * @param transactionRequest
 * @return A pending {@code CoinbaseTransaction} representing the desired {@code CoinbaseRequestMoneyRequest}.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/transactions/request_money.html">coinbase.com/api/doc/1.0/transactions/request_money.html</a>
 */
public CoinbaseTransaction requestMoneyCoinbaseRequest(CoinbaseRequestMoneyRequest transactionRequest) throws IOException {
  final CoinbaseTransaction pendingTransaction=coinbase.requestMoney(new CoinbaseTransaction(transactionRequest),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return handleResponse(pendingTransaction);
}
