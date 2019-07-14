private org.apache.http.HttpVersion createVersion(final FullHttpRequest request){
  HttpVersion protocolVersion=request.protocolVersion();
  return new org.apache.http.HttpVersion(protocolVersion.majorVersion(),protocolVersion.minorVersion());
}
