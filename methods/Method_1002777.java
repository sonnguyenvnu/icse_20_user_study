private void convert(int streamId,HttpHeaders inHeaders,io.netty.handler.codec.http.HttpHeaders outHeaders,boolean trailer,boolean isRequest) throws Http2Exception {
  ArmeriaHttpUtil.toNettyHttp1(streamId,inHeaders,outHeaders,HttpVersion.HTTP_1_1,trailer,isRequest);
  outHeaders.remove(ExtensionHeaderNames.STREAM_ID.text());
  if (server) {
    outHeaders.remove(ExtensionHeaderNames.SCHEME.text());
  }
 else {
    outHeaders.remove(ExtensionHeaderNames.PATH.text());
  }
}
