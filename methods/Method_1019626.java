public String resolve(String uri){
  if (uri.startsWith(SLASH)) {
    return baseURL + uri.substring(1,uri.length());
  }
  return baseURL + uri;
}
