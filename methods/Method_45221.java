public static HttpRequest newRequest(final FullHttpRequest request){
  QueryStringDecoder decoder=new QueryStringDecoder(request.uri());
  ImmutableMap<String,String[]> queries=toQueries(decoder);
  return builder().withVersion(HttpProtocolVersion.versionOf(request.protocolVersion().text())).withHeaders(toHeaders(request)).withMethod(HttpMethod.valueOf(request.method().toString().toUpperCase())).withUri(decoder.path()).withQueries(queries).withContent(toMessageContent(request)).build();
}
