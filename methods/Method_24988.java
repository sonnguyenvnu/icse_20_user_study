/** 
 * Internally used by the webserver to add all queued cookies into the Response's HTTP Headers.
 * @param response The Response object to which headers the queued cookies will be added.
 */
public void unloadQueue(Response response){
  for (  Cookie cookie : this.queue) {
    response.addCookieHeader(cookie.getHTTPHeader());
  }
}
