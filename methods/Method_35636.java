public static MappingBuilder proxyAllTo(String url){
  return any(anyUrl()).willReturn(aResponse().proxiedFrom(url));
}
