private void parseHeader(FullHttpRequest httpRequest,SofaRequest sofaRequest){
  HttpHeaders headers=httpRequest.headers();
  byte serializeType;
  if (httpRequest.method() == HttpMethod.GET) {
    serializeType=0;
  }
 else {
    String codeName=headers.get(RemotingConstants.HEAD_SERIALIZE_TYPE);
    if (codeName != null) {
      serializeType=HttpTransportUtils.getSerializeTypeByName(codeName);
    }
 else {
      String contentType=headers.get(HttpHeaderNames.CONTENT_TYPE);
      serializeType=HttpTransportUtils.getSerializeTypeByContentType(contentType);
    }
  }
  sofaRequest.setSerializeType(serializeType);
  sofaRequest.setTargetAppName(headers.get(RemotingConstants.HEAD_TARGET_APP));
}
