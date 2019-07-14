@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  try {
    byte[] content=toByteArray(Resources.getResource(getFilePath()).openStream());
    return responseDefinition().withStatus(200).withBody(content).withHeader(CONTENT_TYPE,getMimeType()).build();
  }
 catch (  IOException e) {
    return responseDefinition().withStatus(500).build();
  }
}
