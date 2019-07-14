@SuppressWarnings("unchecked") private void writeMetric(String json,HttpServerResponse<O> response){
  byte[] bytes=json.getBytes(Charset.defaultCharset());
  ByteBuf byteBuf=UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length + EXTRA_SPACE);
  byteBuf.writeBytes(HEADER);
  byteBuf.writeBytes(bytes);
  byteBuf.writeBytes(FOOTER);
  response.writeAndFlush((O)byteBuf);
}
