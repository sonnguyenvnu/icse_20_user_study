/** 
 * ??OKEx??????(??)
 * @return
 * @throws IOException
 */
public OkCoinFuturesUserInfoCross getFutureUserInfo() throws IOException {
  OkCoinFuturesUserInfoCross futuresUserInfoCross=okCoin.getFuturesUserInfoCross(apikey,signatureCreator());
  return returnOrThrow(futuresUserInfoCross);
}
