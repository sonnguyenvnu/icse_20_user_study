@Override protected boolean matchName(HttpRequest request){
  HttpHeaders httpHeaders=request.getHeaders();
  return httpHeaders.containsKey(this.name);
}
