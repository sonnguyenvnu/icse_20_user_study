/** 
 * Corresponds to <code>GET withdrawals/history</code>
 * @param cryptoRequest
 * @return
 * @throws IOException
 */
public AbucoinsWithdrawalsHistory abucoinsWithdrawalsHistory() throws IOException {
  AbucoinsWithdrawalsHistory history=abucoinsAuthenticated.withdrawalsHistory(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
  if (history.getHistory().length > 0 && history.getHistory()[0].getMessage() != null)   throw new ExchangeException(history.getHistory()[0].getMessage());
  return history;
}
