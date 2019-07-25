@Override protected int read(InputStream src,ByteBuf dst) throws IOException {
  return dst.writeBytes(src,dst.writableBytes());
}
