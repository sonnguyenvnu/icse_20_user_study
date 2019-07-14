public String getSignatureBaseString(HttpServletRequest request){
  SortedMap<String,SortedSet<String>> significantParameters=loadSignificantParametersForSignatureBaseString(request);
  StringBuilder queryString=new StringBuilder();
  Iterator<Map.Entry<String,SortedSet<String>>> paramIt=significantParameters.entrySet().iterator();
  while (paramIt.hasNext()) {
    Map.Entry<String,SortedSet<String>> sortedParameter=paramIt.next();
    Iterator<String> valueIt=sortedParameter.getValue().iterator();
    while (valueIt.hasNext()) {
      String parameterValue=valueIt.next();
      queryString.append(sortedParameter.getKey()).append('=').append(parameterValue);
      if (paramIt.hasNext() || valueIt.hasNext()) {
        queryString.append('&');
      }
    }
  }
  String url=getBaseUrl(request);
  if (url == null) {
    url=request.getRequestURL().toString();
  }
  url=normalizeUrl(url);
  url=oauthEncode(url);
  String method=request.getMethod().toUpperCase();
  return new StringBuilder(method).append('&').append(url).append('&').append(oauthEncode(queryString.toString())).toString();
}
