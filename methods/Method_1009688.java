@Override protected Footer init() throws IOException {
  long size=fileChannel.size();
  ByteBuffer footerData=read(size - Footer.ENCODED_LENGTH,Footer.ENCODED_LENGTH);
  return Footer.readFooter(Slices.copiedBuffer(footerData));
}
