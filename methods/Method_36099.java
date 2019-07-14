@Override public String getAbsoluteUrl(){
  return withQueryStringIfPresent(request.getRequestURL().toString());
}
