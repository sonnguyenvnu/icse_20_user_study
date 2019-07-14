private String getPath(HttpRequest request){
  URI uri=request.getURI();
  return uri.getPath();
}
