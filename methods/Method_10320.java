/** 
 * Returns cookie decoded from cookie string
 * @param cookieString string of cookie as returned from http request
 * @return decoded cookie or null if exception occured
 */
protected Cookie decodeCookie(String cookieString){
  byte[] bytes=hexStringToByteArray(cookieString);
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
  Cookie cookie=null;
  try {
    ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
    cookie=((SerializableCookie)objectInputStream.readObject()).getCookie();
  }
 catch (  IOException e) {
    AsyncHttpClient.log.d(LOG_TAG,"IOException in decodeCookie",e);
  }
catch (  ClassNotFoundException e) {
    AsyncHttpClient.log.d(LOG_TAG,"ClassNotFoundException in decodeCookie",e);
  }
  return cookie;
}
