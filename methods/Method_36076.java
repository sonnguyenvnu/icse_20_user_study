@Override public boolean authenticate(Request request){
  List<String> headerValues=FluentIterable.from(credentials).transform(new Function<BasicCredentials,String>(){
    @Override public String apply(    BasicCredentials input){
      return input.asAuthorizationHeaderValue();
    }
  }
).toList();
  return request.containsHeader(AUTHORIZATION) && headerValues.contains(request.header(AUTHORIZATION).firstValue());
}
