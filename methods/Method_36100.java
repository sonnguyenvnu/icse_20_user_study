private String withQueryStringIfPresent(String url){
  return url + (isNullOrEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
}
