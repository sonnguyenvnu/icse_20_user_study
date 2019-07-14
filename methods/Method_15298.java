/** 
 * ?????????
 * @param isHttps
 * @return
 */
public static String getCurrentServerAddress(boolean isHttps){
  return isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : getServerAddress(isOnTestMode);
}
