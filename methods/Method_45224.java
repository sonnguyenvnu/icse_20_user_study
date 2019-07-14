public static HttpResponse newResponse(final FullHttpResponse response){
  return builder().withVersion(toHttpProtocolVersion(response.protocolVersion())).withStatus(response.status().code()).withHeaders(toHeaders(response)).withContent(content().withContent(new ByteBufInputStream(response.content())).build()).build();
}
