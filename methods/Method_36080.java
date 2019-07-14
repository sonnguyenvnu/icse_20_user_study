@Override public boolean authenticate(Request request){
  HttpHeader requestHeader=request.header(key);
  if (requestHeader == null || !requestHeader.isPresent()) {
    return false;
  }
  List<String> headerValues=requestHeader.values();
  return request.containsHeader(AUTHORIZATION) && headerValues.contains(value);
}
