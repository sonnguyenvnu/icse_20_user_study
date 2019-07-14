/** 
 * Calculate the current URI given the request.
 * @param request The request.
 * @return The current uri.
 */
protected String calculateCurrentUri(HttpServletRequest request) throws UnsupportedEncodingException {
  ServletUriComponentsBuilder builder=ServletUriComponentsBuilder.fromRequest(request);
  String queryString=request.getQueryString();
  boolean legalSpaces=queryString != null && queryString.contains("+");
  if (legalSpaces) {
    builder.replaceQuery(queryString.replace("+","%20"));
  }
  UriComponents uri=null;
  try {
    uri=builder.replaceQueryParam("code").build(true);
  }
 catch (  IllegalArgumentException ex) {
    return null;
  }
  String query=uri.getQuery();
  if (legalSpaces) {
    query=query.replace("%20","+");
  }
  return ServletUriComponentsBuilder.fromUri(uri.toUri()).replaceQuery(query).build().toString();
}
