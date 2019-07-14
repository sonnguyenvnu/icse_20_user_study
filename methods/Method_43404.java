/** 
 * ?????
 * @param entrustSheetId
 * @return
 * @throws IOException
 */
public BitZTradeCancelResult cancelEntrustSheet(String entrustSheetId) throws IOException {
  return bitz.cancelEntrustSheet(apiKey,getTimeStamp(),nonce,signer,entrustSheetId);
}
