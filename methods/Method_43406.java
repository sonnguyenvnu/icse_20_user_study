/** 
 * ???????
 * @param entrustSheetId
 * @return
 * @throws IOException
 */
public BitZEntrustSheetInfoResult getEntrustSheetInfo(String entrustSheetId) throws IOException {
  return bitz.getEntrustSheetInfo(apiKey,getTimeStamp(),nonce,signer,entrustSheetId);
}
