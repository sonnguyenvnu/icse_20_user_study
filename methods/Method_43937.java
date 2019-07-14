/** 
 * Request a digital currency address to fund this account. Allows to fund the exchange account with digital currency from an external address
 * @param currency The digital currency that corresponds to the desired deposit address.
 * @param args Necessary argument(s) as a {@code String}
 * @return the internal deposit address to send funds to
 * @throws ExchangeException - Indication that the exchange reported some kind of error with therequest or response
 * @throws NotAvailableFromExchangeException - Indication that the exchange does not support therequested function or data
 * @throws NotYetImplementedForExchangeException - Indication that the exchange supports therequested function or data, but it has not yet been implemented
 * @throws IOException - Indication that a networking error occurred while fetching JSON data
 */
default String requestDepositAddress(Currency currency,String... args) throws IOException {
  throw new NotYetImplementedForExchangeException();
}
