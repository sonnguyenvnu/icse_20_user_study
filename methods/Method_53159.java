public List<HttpParameter> generateOAuthSignatureHttpParams(String method,String url){
  long timestamp=System.currentTimeMillis() / 1000;
  long nonce=timestamp + RAND.nextInt();
  List<HttpParameter> oauthHeaderParams=new ArrayList<HttpParameter>(5);
  oauthHeaderParams.add(new HttpParameter("oauth_consumer_key",consumerKey));
  oauthHeaderParams.add(OAUTH_SIGNATURE_METHOD);
  oauthHeaderParams.add(new HttpParameter("oauth_timestamp",timestamp));
  oauthHeaderParams.add(new HttpParameter("oauth_nonce",nonce));
  oauthHeaderParams.add(new HttpParameter("oauth_version","1.0"));
  if (oauthToken != null) {
    oauthHeaderParams.add(new HttpParameter("oauth_token",oauthToken.getToken()));
  }
  List<HttpParameter> signatureBaseParams=new ArrayList<HttpParameter>(oauthHeaderParams.size());
  signatureBaseParams.addAll(oauthHeaderParams);
  parseGetParameters(url,signatureBaseParams);
  StringBuilder base=new StringBuilder(method).append("&").append(HttpParameter.encode(constructRequestURL(url))).append("&");
  base.append(HttpParameter.encode(normalizeRequestParameters(signatureBaseParams)));
  String oauthBaseString=base.toString();
  String signature=generateSignature(oauthBaseString,oauthToken);
  oauthHeaderParams.add(new HttpParameter("oauth_signature",signature));
  return oauthHeaderParams;
}
