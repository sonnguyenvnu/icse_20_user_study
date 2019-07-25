private String canonicalise(String url){
  if (isAbsolute && !url.startsWith("/"))   return "/" + url;
  return url;
}
