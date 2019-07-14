/** 
 * Corresponds to <code>POST /transfer</code> end point.
 * @param currency cryptocurrency to transfer
 * @param quantity amount of cryptocurrency, which will be transferred
 * @param address wallet address of receiver
 * @return Success of transfer
 * @throws ExchangeException if an error occurred.
 */
public BitbayBaseResponse transfer(Currency currency,BigDecimal quantity,String address){
  BitbayBaseResponse resp=bitbayAuthenticated.transfer(apiKey,sign,exchange.getNonceFactory(),currency.getCurrencyCode(),quantity.toString(),address);
  if (resp.getMessage() != null)   throw new ExchangeException(resp.getMessage());
  return resp;
}
