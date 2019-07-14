public FullHttpRequest toFullHttpRequest(){
  ByteBuf buffer=Unpooled.buffer();
  MessageContent content=getContent();
  if (content != null) {
    buffer.writeBytes(content.getContent());
  }
  QueryStringEncoder encoder=new QueryStringEncoder(uri);
  for (  Map.Entry<String,String[]> entry : queries.entrySet()) {
    String[] values=entry.getValue();
    for (    String value : values) {
      encoder.addParam(entry.getKey(),value);
    }
  }
  FullHttpRequest request=new DefaultFullHttpRequest(HttpVersion.valueOf(getVersion().text()),io.netty.handler.codec.http.HttpMethod.valueOf(method.name()),encoder.toString(),buffer);
  for (  Map.Entry<String,String[]> entry : getHeaders().entrySet()) {
    String key=entry.getKey();
    for (    String value : entry.getValue()) {
      request.headers().add(key,value);
    }
  }
  return request;
}
