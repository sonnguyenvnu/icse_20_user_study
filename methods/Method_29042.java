/** 
 * UTF-8??
 * @param link
 * @return
 */
public static String doGet(String link){
  return doGet(link,"UTF-8",HTTP_CONNECTION_TIMEOUT,HTTP_SOCKET_TIMEOUT);
}
