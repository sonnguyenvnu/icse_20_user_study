/** 
 * Authenticated resource that lets you convert Bitcoin crediting your primary bank account on Coinbase. (You must link and verify your bank account through the website before this API call will work).
 * @see <a
   *     href="https://developers.coinbase.com/api/v2#place-sell-order">developers.coinbase.com/api/v2#place-sell-order</a>
 */
public CoinbaseSell quote(String accountId,BigDecimal total,Currency currency) throws IOException {
  return sellInternal(accountId,new SellPayload(total,currency.getCurrencyCode(),false,true));
}
