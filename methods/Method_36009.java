public RequestPatternBuilder withUrl(String url){
  this.url=WireMock.urlEqualTo(url);
  return this;
}
