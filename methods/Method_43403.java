/** 
 * ?????(???)
 * @param symbol
 * @param type ???? 1?? 2 ??
 * @param price
 * @param number
 * @return
 * @throws IOException
 */
public BitZTradeAddResult addEntrustSheet(String symbol,String type,BigDecimal price,BigDecimal number) throws IOException {
  return bitz.addEntrustSheet(apiKey,symbol,getTimeStamp(),nonce,signer,type,price,number,tradePwd);
}
