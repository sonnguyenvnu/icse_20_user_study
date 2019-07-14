/** 
 * sets HTTP headers
 * @param req        The request
 * @param connection HttpURLConnection
 */
private void setHeaders(HttpRequest req,HttpURLConnection connection){
  if (logger.isDebugEnabled()) {
    logger.debug("Request: ");
    logger.debug(req.getMethod().name() + " ",req.getURL());
  }
  String authorizationHeader;
  if (req.getAuthorization() != null && (authorizationHeader=req.getAuthorization().getAuthorizationHeader(req)) != null) {
    if (logger.isDebugEnabled()) {
      logger.debug("Authorization: ",authorizationHeader.replaceAll(".","*"));
    }
    connection.addRequestProperty("Authorization",authorizationHeader);
  }
  if (req.getRequestHeaders() != null) {
    for (    String key : req.getRequestHeaders().keySet()) {
      connection.addRequestProperty(key,req.getRequestHeaders().get(key));
      logger.debug(key + ": " + req.getRequestHeaders().get(key));
    }
  }
}
