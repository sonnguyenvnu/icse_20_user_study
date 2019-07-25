private String message(final Response response){
  final Readable reader;
  try {
    reader=new InputStreamReader(response.readEntity(InputStream.class),UTF_8);
  }
 catch (  IllegalStateException e) {
    return null;
  }
  try {
    return CharStreams.toString(reader);
  }
 catch (  IOException ignore) {
    return null;
  }
}
