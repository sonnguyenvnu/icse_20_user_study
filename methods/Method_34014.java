/** 
 * Parse the OAuth header parameters. The parameters will be oauth-decoded.
 * @param request The request.
 * @return The parsed parameters, or null if no OAuth authorization header was supplied.
 */
protected Map<String,String> parseHeaderParameters(HttpServletRequest request){
  String header=null;
  Enumeration<String> headers=request.getHeaders("Authorization");
  while (headers.hasMoreElements()) {
    String value=headers.nextElement();
    if ((value.toLowerCase().startsWith("oauth "))) {
      header=value;
      break;
    }
  }
  Map<String,String> parameters=null;
  if (header != null) {
    parameters=new HashMap<String,String>();
    String authHeaderValue=header.substring(6);
    String[] headerEntries=StringSplitUtils.splitIgnoringQuotes(authHeaderValue,',');
    for (    Object o : StringSplitUtils.splitEachArrayElementAndCreateMap(headerEntries,"=","\"").entrySet()) {
      Map.Entry entry=(Map.Entry)o;
      try {
        String key=oauthDecode((String)entry.getKey());
        String value=oauthDecode((String)entry.getValue());
        parameters.put(key,value);
      }
 catch (      DecoderException e) {
        throw new IllegalStateException(e);
      }
    }
  }
  return parameters;
}
