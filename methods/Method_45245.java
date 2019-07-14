@Override protected MessageContent responseContent(final HttpRequest httpRequest){
  FileResourceReader reader=new FileResourceReader(asResource(httpRequest));
  return reader.readFor(httpRequest);
}
