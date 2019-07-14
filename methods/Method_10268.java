/** 
 * Set connection timeout limit (milliseconds). By default, this is set to 10 seconds.
 * @param value Connection timeout in milliseconds, minimal value is 1000 (1 second).
 */
public void setConnectTimeout(int value){
  connectTimeout=value < 1000 ? DEFAULT_SOCKET_TIMEOUT : value;
  final HttpParams httpParams=httpClient.getParams();
  ConnManagerParams.setTimeout(httpParams,connectTimeout);
  HttpConnectionParams.setConnectionTimeout(httpParams,connectTimeout);
}
