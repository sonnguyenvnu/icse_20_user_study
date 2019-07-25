private void fail(int id,HttpResponseStatus status,@Nullable HttpData content){
  discarding=true;
  req=null;
  final HttpData data=content != null ? content : HttpData.ofUtf8(status.toString());
  final ResponseHeaders headers=ResponseHeaders.builder().status(status.code()).set(HttpHeaderNames.CONNECTION,"close").setObject(HttpHeaderNames.CONTENT_TYPE,MediaType.PLAIN_TEXT_UTF_8).setInt(HttpHeaderNames.CONTENT_LENGTH,data.length()).build();
  writer.writeHeaders(id,1,headers,false);
  writer.writeData(id,1,data,true).addListener(ChannelFutureListener.CLOSE);
}
