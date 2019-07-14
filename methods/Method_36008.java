public static RequestPatternBuilder allRequests(){
  return new RequestPatternBuilder(RequestMethod.ANY,WireMock.anyUrl());
}
