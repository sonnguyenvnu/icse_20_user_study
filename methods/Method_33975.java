/** 
 * Loads the OAuth parameters for the given resource at the given URL and the given token. These parameters include any query parameters on the URL since they are included in the signature. The oauth parameters are NOT encoded.
 * @param details      The resource details.
 * @param requestURL   The request URL.
 * @param requestToken The request token.
 * @param httpMethod   The http method.
 * @param additionalParameters Additional oauth parameters (outside of the core oauth spec).
 * @return The parameters.
 */
protected Map<String,Set<CharSequence>> loadOAuthParameters(ProtectedResourceDetails details,URL requestURL,OAuthConsumerToken requestToken,String httpMethod,Map<String,String> additionalParameters){
  Map<String,Set<CharSequence>> oauthParams=new TreeMap<String,Set<CharSequence>>();
  if (additionalParameters != null) {
    for (    Map.Entry<String,String> additionalParam : additionalParameters.entrySet()) {
      Set<CharSequence> values=oauthParams.get(additionalParam.getKey());
      if (values == null) {
        values=new HashSet<CharSequence>();
        oauthParams.put(additionalParam.getKey(),values);
      }
      if (additionalParam.getValue() != null) {
        values.add(additionalParam.getValue());
      }
    }
  }
  String query=requestURL.getQuery();
  if (query != null) {
    StringTokenizer queryTokenizer=new StringTokenizer(query,"&");
    while (queryTokenizer.hasMoreElements()) {
      String token=(String)queryTokenizer.nextElement();
      CharSequence value=null;
      int equalsIndex=token.indexOf('=');
      if (equalsIndex < 0) {
        token=urlDecode(token);
      }
 else {
        value=new QueryParameterValue(urlDecode(token.substring(equalsIndex + 1)));
        token=urlDecode(token.substring(0,equalsIndex));
      }
      Set<CharSequence> values=oauthParams.get(token);
      if (values == null) {
        values=new HashSet<CharSequence>();
        oauthParams.put(token,values);
      }
      if (value != null) {
        values.add(value);
      }
    }
  }
  String tokenSecret=requestToken == null ? null : requestToken.getSecret();
  String nonce=getNonceFactory().generateNonce();
  oauthParams.put(OAuthConsumerParameter.oauth_consumer_key.toString(),Collections.singleton((CharSequence)details.getConsumerKey()));
  if ((requestToken != null) && (requestToken.getValue() != null)) {
    oauthParams.put(OAuthConsumerParameter.oauth_token.toString(),Collections.singleton((CharSequence)requestToken.getValue()));
  }
  oauthParams.put(OAuthConsumerParameter.oauth_nonce.toString(),Collections.singleton((CharSequence)nonce));
  oauthParams.put(OAuthConsumerParameter.oauth_signature_method.toString(),Collections.singleton((CharSequence)details.getSignatureMethod()));
  oauthParams.put(OAuthConsumerParameter.oauth_timestamp.toString(),Collections.singleton((CharSequence)String.valueOf(System.currentTimeMillis() / 1000)));
  oauthParams.put(OAuthConsumerParameter.oauth_version.toString(),Collections.singleton((CharSequence)"1.0"));
  String signatureBaseString=getSignatureBaseString(oauthParams,requestURL,httpMethod);
  OAuthSignatureMethod signatureMethod;
  try {
    signatureMethod=getSignatureFactory().getSignatureMethod(details.getSignatureMethod(),details.getSharedSecret(),tokenSecret);
  }
 catch (  UnsupportedSignatureMethodException e) {
    throw new OAuthRequestFailedException(e.getMessage(),e);
  }
  String signature=signatureMethod.sign(signatureBaseString);
  oauthParams.put(OAuthConsumerParameter.oauth_signature.toString(),Collections.singleton((CharSequence)signature));
  return oauthParams;
}
