/** 
 * Corresponds to <code>GET deposits/history</code>
 * @param cryptoRequest
 * @return
 * @throws IOException
 */
public AbucoinsDepositsHistory abucoinsDepositHistory() throws IOException {
  AbucoinsDepositsHistory history=abucoinsAuthenticated.depositsHistory(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
  if (history.getHistory().length > 0 && history.getHistory()[0].getMessage() != null)   throw new ExchangeException(history.getHistory()[0].getMessage());
  return history;
}
