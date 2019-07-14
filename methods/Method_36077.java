@Override public List<HttpHeader> generateAuthHeaders(){
  BasicCredentials basicCredentials=new BasicCredentials(username,password);
  return singletonList(httpHeader(AUTHORIZATION,basicCredentials.asAuthorizationHeaderValue()));
}
