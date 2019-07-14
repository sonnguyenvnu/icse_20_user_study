/** 
 * Obtain your own personal asset information.
 * @return Object
 * @throws IOException
 */
public EXXAccountInformation getExxAccountInfo() throws IOException {
  Long nonce=System.currentTimeMillis();
  String params="accesskey=" + this.apiKey + "&nonce=" + nonce;
  String signature=CommonUtil.HmacSHA512(params,this.secretKey);
  return exxAuthenticated.getAccountInfo(this.apiKey,nonce,signature);
}
