/** 
 * ??????
 * @param ids
 * @return
 * @throws IOException
 */
public BitZTradeCancelListResult cancelAllEntrustSheet(String ids) throws IOException {
  return bitz.cancelAllEntrustSheet(apiKey,getTimeStamp(),nonce,signer,ids);
}
