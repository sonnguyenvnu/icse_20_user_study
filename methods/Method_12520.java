@Override public HttpHeaders getHeaders(Instance instance){
  HttpHeaders headers=new HttpHeaders();
  delegates.forEach(delegate -> headers.addAll(delegate.getHeaders(instance)));
  return headers;
}
