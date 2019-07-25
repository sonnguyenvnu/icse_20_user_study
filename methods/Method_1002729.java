@Override protected String reproduce(HttpHeaders headers,ByteBuf wrappedBuffer){
  final String produced=wrappedBuffer.toString(wrappedBuffer.readerIndex(),wrappedBuffer.readableBytes(),charset);
  if (produced.length() > length) {
    return produced.substring(0,length);
  }
 else {
    return produced;
  }
}
