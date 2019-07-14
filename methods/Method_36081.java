@Override public List<HttpHeader> generateAuthHeaders(){
  return Collections.singletonList(new HttpHeader(key,value));
}
