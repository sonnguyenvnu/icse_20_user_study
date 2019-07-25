public String resolve(String uri){
  StringBuilder url=new StringBuilder(baseURL);
  url.append("&");
  url.append(ENTRY_NAME_HTTP_PARAM);
  url.append("=");
  url.append(uri);
  return url.toString();
}
