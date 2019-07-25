/** 
 * Gets a working GoogleServerConnection, or null if the GoogleServerConnection cannot be made to work. Blocks for the duration of bootstrap if it has not yet succeeded. This method performs network activity, so it cannot be called on the application's main thread.
 * @param db GoogleServerDatabase provides the IP addresses for this connection.
 * @param interceptor Interceptor user defined interceptor; useful for testing, may be null.
 */
@WorkerThread public static GoogleServerConnection get(GoogleServerDatabase db,Interceptor interceptor){
  GoogleServerConnection s=new GoogleServerConnection(db,interceptor);
  DualStackResult redirects=s.bootstrap();
  if (redirects == null) {
    return null;
  }
  db.setPreferred(redirects);
  return s;
}
