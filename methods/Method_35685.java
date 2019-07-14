public byte[] readContents(){
  try (InputStream stream=getStream()){
    return ByteStreams.toByteArray(stream);
  }
 catch (  final IOException ioe) {
    return throwUnchecked(ioe,byte[].class);
  }
}
