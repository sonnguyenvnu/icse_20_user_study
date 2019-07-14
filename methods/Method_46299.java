private static Http2Headers http1HeadersToHttp2Headers(FullHttpRequest request){
  CharSequence host=request.headers().get(HttpHeaderNames.HOST);
  Http2Headers http2Headers=new DefaultHttp2Headers().method(HttpMethod.GET.asciiName()).path(request.uri()).scheme(HttpScheme.HTTP.name());
  if (host != null) {
    http2Headers.authority(host);
  }
  return http2Headers;
}
