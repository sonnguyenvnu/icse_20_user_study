public FullHttpResponse toFullResponse(){
  FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.valueOf(this.version.text()),HttpResponseStatus.valueOf(this.status));
  for (  Map.Entry<String,String[]> entry : getHeaders().entrySet()) {
    String key=entry.getKey();
    for (    String value : entry.getValue()) {
      response.headers().add(key,value);
    }
  }
  if (this.content != null) {
    response.content().writeBytes(this.content.getContent());
  }
  return response;
}
