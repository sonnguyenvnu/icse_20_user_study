public void addPart(String key,String streamName,InputStream inputStream,String type) throws IOException {
  out.write(boundaryLine);
  out.write(createContentDisposition(key,streamName));
  out.write(createContentType(type));
  out.write(TRANSFER_ENCODING_BINARY);
  out.write(CR_LF);
  final byte[] tmp=new byte[4096];
  int l;
  while ((l=inputStream.read(tmp)) != -1) {
    out.write(tmp,0,l);
  }
  out.write(CR_LF);
  out.flush();
}
