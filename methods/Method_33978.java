/** 
 * Get the signature base string for the specified parameters. It is presumed the parameters are NOT OAuth-encoded.
 * @param oauthParams The parameters (NOT oauth-encoded).
 * @param requestURL  The request URL.
 * @param httpMethod  The http method.
 * @return The signature base string.
 */
protected String getSignatureBaseString(Map<String,Set<CharSequence>> oauthParams,URL requestURL,String httpMethod){
  TreeMap<String,TreeSet<String>> sortedParameters=new TreeMap<String,TreeSet<String>>();
  for (  Map.Entry<String,Set<CharSequence>> param : oauthParams.entrySet()) {
    String key=oauthEncode(param.getKey());
    TreeSet<String> sortedValues=sortedParameters.get(key);
    if (sortedValues == null) {
      sortedValues=new TreeSet<String>();
      sortedParameters.put(key,sortedValues);
    }
    for (    CharSequence value : param.getValue()) {
      sortedValues.add(oauthEncode(value.toString()));
    }
  }
  StringBuilder queryString=new StringBuilder();
  Iterator<Map.Entry<String,TreeSet<String>>> sortedIt=sortedParameters.entrySet().iterator();
  while (sortedIt.hasNext()) {
    Map.Entry<String,TreeSet<String>> sortedParameter=sortedIt.next();
    for (Iterator<String> sortedParametersIterator=sortedParameter.getValue().iterator(); sortedParametersIterator.hasNext(); ) {
      String parameterValue=sortedParametersIterator.next();
      if (parameterValue == null) {
        parameterValue="";
      }
      queryString.append(sortedParameter.getKey()).append('=').append(parameterValue);
      if (sortedIt.hasNext() || sortedParametersIterator.hasNext()) {
        queryString.append('&');
      }
    }
  }
  StringBuilder url=new StringBuilder(requestURL.getProtocol().toLowerCase()).append("://").append(requestURL.getHost().toLowerCase());
  if ((requestURL.getPort() >= 0) && (requestURL.getPort() != requestURL.getDefaultPort())) {
    url.append(":").append(requestURL.getPort());
  }
  url.append(requestURL.getPath());
  return new StringBuilder(httpMethod.toUpperCase()).append('&').append(oauthEncode(url.toString())).append('&').append(oauthEncode(queryString.toString())).toString();
}
