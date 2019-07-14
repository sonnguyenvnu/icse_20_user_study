private void parseHttp2Request(Http2Headers headers,SofaRequest sofaRequest){
  String targetApp=StringUtils.toString(headers.get(RemotingConstants.HEAD_TARGET_APP));
  sofaRequest.setTargetAppName(targetApp);
  byte serializeType;
  CharSequence codeName=headers.get(RemotingConstants.HEAD_SERIALIZE_TYPE);
  if (codeName != null) {
    serializeType=HttpTransportUtils.getSerializeTypeByName(codeName.toString());
  }
 else {
    String contentType=StringUtils.toString(headers.get(HttpHeaderNames.CONTENT_TYPE));
    serializeType=HttpTransportUtils.getSerializeTypeByContentType(contentType);
  }
  sofaRequest.setSerializeType(serializeType);
  Map<String,String> traceMap=new HashMap<String,String>(8);
  Iterator<Map.Entry<CharSequence,CharSequence>> it=headers.iterator();
  while (it.hasNext()) {
    Map.Entry<CharSequence,CharSequence> entry=it.next();
    String key=entry.getKey().toString();
    if (HttpTracerUtils.isTracerKey(key)) {
      HttpTracerUtils.parseTraceKey(traceMap,key,StringUtils.toString(entry.getValue()));
    }
 else     if (!key.startsWith(":")) {
      sofaRequest.addRequestProp(key,StringUtils.toString(entry.getValue()));
    }
  }
  if (!traceMap.isEmpty()) {
    sofaRequest.addRequestProp(RemotingConstants.RPC_TRACE_NAME,traceMap);
  }
}
