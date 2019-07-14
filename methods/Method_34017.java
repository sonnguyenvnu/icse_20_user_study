/** 
 * Loads the significant parameters (name-to-value map) that are to be used to calculate the signature base string. The parameters will be encoded, per the spec section 9.1.
 * @param request The request.
 * @return The significan parameters.
 */
protected SortedMap<String,SortedSet<String>> loadSignificantParametersForSignatureBaseString(HttpServletRequest request){
  SortedMap<String,SortedSet<String>> significantParameters=new TreeMap<String,SortedSet<String>>();
  Enumeration parameterNames=request.getParameterNames();
  while (parameterNames.hasMoreElements()) {
    String parameterName=(String)parameterNames.nextElement();
    String[] values=request.getParameterValues(parameterName);
    if (values == null) {
      values=new String[]{""};
    }
    parameterName=oauthEncode(parameterName);
    for (    String parameterValue : values) {
      if (parameterValue == null) {
        parameterValue="";
      }
      parameterValue=oauthEncode(parameterValue);
      SortedSet<String> significantValues=significantParameters.get(parameterName);
      if (significantValues == null) {
        significantValues=new TreeSet<String>();
        significantParameters.put(parameterName,significantValues);
      }
      significantValues.add(parameterValue);
    }
  }
  Map<String,String> oauthParams=parseParameters(request);
  oauthParams.remove("realm");
  Set<String> parsedParams=oauthParams.keySet();
  for (  String parameterName : parsedParams) {
    String parameterValue=oauthParams.get(parameterName);
    if (parameterValue == null) {
      parameterValue="";
    }
    parameterName=oauthEncode(parameterName);
    parameterValue=oauthEncode(parameterValue);
    SortedSet<String> significantValues=significantParameters.get(parameterName);
    if (significantValues == null) {
      significantValues=new TreeSet<String>();
      significantParameters.put(parameterName,significantValues);
    }
    significantValues.add(parameterValue);
  }
  significantParameters.remove(OAuthConsumerParameter.oauth_signature.toString());
  return significantParameters;
}
