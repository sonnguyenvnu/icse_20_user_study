/** 
 * Attempts to encode response bytes as string of set encoding
 * @param charset     charset to create string with
 * @param stringBytes response bytes
 * @return String of set encoding or null
 */
public static String getResponseString(byte[] stringBytes,String charset){
  try {
    String toReturn=(stringBytes == null) ? null : new String(stringBytes,charset);
    if (toReturn != null && toReturn.startsWith(UTF8_BOM)) {
      return toReturn.substring(1);
    }
    return toReturn;
  }
 catch (  UnsupportedEncodingException e) {
    AsyncHttpClient.log.e(LOG_TAG,"Encoding response into string failed",e);
    return null;
  }
}
