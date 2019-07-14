private HttpHeaders getHeadersForTokenRequest(AccessTokenRequest request){
  HttpHeaders headers=new HttpHeaders();
  headers.putAll(request.getHeaders());
  if (request.getCookie() != null) {
    headers.set("Cookie",request.getCookie());
  }
  return headers;
}
