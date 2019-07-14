/** 
 * Get the trading fees per currency pair as determined by the given exchange's rules for adjusting fees by recent volume traded. Some exchanges will provide the current fees per currency via a single API request, while others require more logic to compute by hand.
 * @return map between currency pairs and their fees at the time of invocation.
 * @throws ExchangeException - Indication that the exchange reported some kind of error with therequest or response
 * @throws NotAvailableFromExchangeException - Indication that the exchange does not support therequested function or data
 * @throws NotYetImplementedForExchangeException - Indication that the exchange supports therequested function or data, but it has not yet been implemented
 * @throws IOException - Indication that a networking error occurred while fetching JSON data
 */
public default Map<CurrencyPair,Fee> getDynamicTradingFees() throws IOException {
  throw new NotYetImplementedForExchangeException();
}
