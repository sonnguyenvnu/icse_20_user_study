private static InputStream wrapToMarkSupportedStream(InputStream inputStream){
  if (!inputStream.markSupported()) {
    inputStream=new BufferedInputStream(inputStream,HEADER_SIZE);
  }
  return inputStream;
}
