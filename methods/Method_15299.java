/** 
 * ???????
 * @param isTest
 * @return
 */
public static String getServerAddress(boolean isTest,boolean isHttps){
  SharedPreferences sdf=context.getSharedPreferences(APP_SETTING,Context.MODE_PRIVATE);
  if (sdf == null) {
    return null;
  }
  if (isTest) {
    return sdf.getString(KEY_SERVER_ADDRESS_TEST,URL_SERVER_ADDRESS_TEST);
  }
  return sdf.getString(KEY_SERVER_ADDRESS_NORMAL,isHttps ? URL_SERVER_ADDRESS_NORMAL_HTTPS : URL_SERVER_ADDRESS_NORMAL_HTTP);
}
