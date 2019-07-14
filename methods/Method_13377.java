@Override protected boolean matchValue(HttpRequest request){
  HttpHeaders httpHeaders=request.getHeaders();
  String headerValue=httpHeaders.getFirst(this.name);
  return ObjectUtils.nullSafeEquals(this.value,headerValue);
}
