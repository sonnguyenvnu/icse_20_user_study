protected byte[] doReadFor(final Request request){
  String actualFilename=this.filename(request);
  URL resource=Resources.getResource(actualFilename);
  if (resource == null) {
    throw new IllegalArgumentException(format("%s does not exist",actualFilename));
  }
  try {
    return toByteArray(resource.openStream());
  }
 catch (  IOException e) {
    throw new MocoException(e);
  }
}
