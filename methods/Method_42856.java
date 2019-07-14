/** 
 * Corresponds to <code>POST deposits/make</code>
 * @param cryptoRequest
 * @return
 * @throws IOException
 */
public AbucoinsCryptoDeposit abucoinsDepositMake(AbucoinsCryptoDepositRequest cryptoRequest) throws IOException {
  return abucoinsAuthenticated.depositsMake(cryptoRequest,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
}
