/** 
 * @param currency Currency for getting address
 * @param newAddress 0 - get old address, 1 - generate new address
 * @return address
 * @throws IOException
 */
public String requestAddress(String currency,int newAddress) throws IOException {
  DSXCryptoDepositAddressReturn info=dsx.getCryptoDepositAddress(apiKey,signatureCreator,exchange.getNonceFactory(),currency,newAddress);
  checkResult(info);
  return String.valueOf(info.getReturnValue().getAddress());
}
