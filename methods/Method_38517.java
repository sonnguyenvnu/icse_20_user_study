/** 
 * Uses custom connection provider when  {@link #open() opening} theconnection.
 */
public HttpRequest withConnectionProvider(final HttpConnectionProvider httpConnectionProvider){
  this.httpConnectionProvider=httpConnectionProvider;
  return this;
}
