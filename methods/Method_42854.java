/** 
 * Corresponds to <code>POST withdrawals/crypto</code>
 * @param withdrawRequest
 * @return
 * @throws IOException
 */
public AbucoinsCryptoWithdrawal abucoinsWithdrawalsMake(AbucoinsCryptoWithdrawalRequest withdrawRequest) throws IOException {
  return abucoinsAuthenticated.withdrawalsMake(withdrawRequest,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
}
