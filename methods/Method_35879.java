public Collection<HttpHeader> all(){
  List<HttpHeader> httpHeaderList=newArrayList();
  for (  CaseInsensitiveKey key : headers.keySet()) {
    httpHeaderList.add(new HttpHeader(key.value(),headers.get(key)));
  }
  return httpHeaderList;
}
