public HttpReq param(String name,String value){
  if (params.length() > 0)   params.append('&');
  try {
    params.append(name).append('=').append(URLEncoder.encode(value,"UTF-8"));
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
  return this;
}
