/** 
 * @param currencyPair
 * @param amount total amount of product to buy using borrowed funds and user's funds
 * @param collateral currency of user funds used, may be one of currencies in the pair, default issecond currency in the pair
 * @param leverage leverage ratio of total funds (user's and borrowed) to user's funds; forexample - leverage=3 means - ratio total/user's=3:1, margin=33.(3)%, 1/3 is users, 2/3 are borrowed; Note that in UI it will be presented as 1/3
 * @param type position type. long - buying product, profitable if product price grows; short -selling product, profitable if product price falls;
 * @param anySlippage allows to open position at changed price
 * @param estimatedOpenPrice allows to open position at changed price
 * @param stopLossPrice price near which your position will be closed automatically in case ofunfavorable market conditions
 * @return CexioPosition
 * @throws IOException
 */
public CexioOpenPosition openCexIOPosition(CurrencyPair currencyPair,BigDecimal amount,Currency collateral,Integer leverage,CexioPositionType type,Boolean anySlippage,BigDecimal estimatedOpenPrice,BigDecimal stopLossPrice) throws IOException {
  CexioOpenPositionResponse order=cexIOAuthenticated.openPosition(signatureCreator,currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode(),new CexIOOpenPositionRequest(currencyPair.base.getCurrencyCode(),amount,collateral.getCurrencyCode(),leverage,type,anySlippage,estimatedOpenPrice,stopLossPrice));
  if (order.getErrorMessage() != null) {
    throw new ExchangeException(order.getErrorMessage());
  }
  return order.getPosition();
}
