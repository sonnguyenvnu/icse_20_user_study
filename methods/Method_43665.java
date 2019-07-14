/** 
 * Authenticated resource that returns contacts the user has previously sent to or received from.
 * @param page Optional parameter to request a desired page of results. Will return page 1 if thesupplied page is null or less than 1.
 * @param limit Optional parameter to limit the maximum number of results to return. Will returnup to 25 results by default if null or less than 1.
 * @param filter Optional String match to filter addresses. Matches the address itself and also ifthe use has set a ‘label’ on the address. No filter is applied if  {@code filter} is null orempty.
 * @return {@code CoinbaseContacts} the user has previously sent to or received from.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/contacts/index.html">coinbase.com/api/doc/1.0/contacts/index.html</a>
 */
public CoinbaseContacts getCoinbaseContacts(Integer page,final Integer limit,final String filter) throws IOException {
  final CoinbaseContacts contacts=coinbase.getContacts(page,limit,filter,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return contacts;
}
