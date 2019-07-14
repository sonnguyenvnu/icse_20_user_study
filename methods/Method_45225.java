private static HttpProtocolVersion toHttpProtocolVersion(final HttpVersion httpVersion){
  return HttpProtocolVersion.versionOf(httpVersion.text());
}
