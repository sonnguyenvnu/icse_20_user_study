protected FullHttpRequest convertToHttpRequest(SofaRequest request){
  HttpScheme scheme=SslContextBuilder.SSL ? HttpScheme.HTTPS : HttpScheme.HTTP;
  AsciiString hostName=new AsciiString(providerInfo.getHost() + ':' + providerInfo.getPort());
  String url="/" + request.getTargetServiceUniqueName() + "/" + request.getMethodName();
  if (LOGGER.isDebugEnabled()) {
    LOGGER.debug("send request to url :{}",url);
  }
  FullHttpRequest httpRequest=new DefaultFullHttpRequest(HTTP_1_1,POST,url,wrappedBuffer(request.getData().array()));
  HttpHeaders headers=httpRequest.headers();
  addToHeader(headers,HttpHeaderNames.HOST,hostName);
  addToHeader(headers,HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(),scheme.name());
  addToHeader(headers,HttpHeaderNames.ACCEPT_ENCODING,HttpHeaderValues.GZIP);
  addToHeader(headers,HttpHeaderNames.ACCEPT_ENCODING,HttpHeaderValues.DEFLATE);
  addToHeader(headers,RemotingConstants.HEAD_SERIALIZE_TYPE,SerializerFactory.getAliasByCode(request.getSerializeType()));
  addToHeader(headers,RemotingConstants.HEAD_TARGET_APP,request.getTargetAppName());
  Map<String,Object> requestProps=request.getRequestProps();
  if (requestProps != null) {
    flatCopyTo("",requestProps,headers);
  }
  return httpRequest;
}
