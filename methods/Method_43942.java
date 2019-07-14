/** 
 * Get the tickers representing the current exchange rate for the provided parameters
 * @return The Tickers, null if some sort of error occurred. Implementers should log the error.
 * @throws ExchangeException - Indication that the exchange reported some kind of error with therequest or response
 * @throws NotAvailableFromExchangeException - Indication that the exchange does not support therequested function or data
 * @throws NotYetImplementedForExchangeException - Indication that the exchange supports therequested function or data, but it has not yet been implemented
 * @throws IOException - Indication that a networking error occurred while fetching JSON data
 */
default List<Ticker> getTickers(Params params) throws IOException {
  throw new NotYetImplementedForExchangeException();
}
