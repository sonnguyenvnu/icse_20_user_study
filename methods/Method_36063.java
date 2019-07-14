@Override public EqualToXmlPattern forRequest(Request request){
  return new EqualToXmlPattern(request.getBodyAsString());
}
