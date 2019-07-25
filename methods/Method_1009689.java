@Override protected Footer init() throws IOException {
  long size=fileChannel.size();
  data=fileChannel.map(MapMode.READ_ONLY,0,size);
  Slice footerSlice=Slices.copiedBuffer(data,(int)size - Footer.ENCODED_LENGTH,Footer.ENCODED_LENGTH);
  return Footer.readFooter(footerSlice);
}
