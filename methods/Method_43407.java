/** 
 * ??????
 * @return
 * @throws IOException
 */
public BitZUserAssetsResult getUserAssets() throws IOException {
  return bitz.getUserAssets(apiKey,getTimeStamp(),nonce,signer);
}
