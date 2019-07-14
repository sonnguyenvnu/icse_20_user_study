private void convertBodyFromFileIfNecessary(StubMapping mapping){
  String bodyFileName=mapping.getResponse().getBodyFileName();
  if (bodyFileName != null) {
    ResponseDefinitionBuilder responseDefinitionBuilder=ResponseDefinitionBuilder.like(mapping.getResponse()).withBodyFile(null);
    String extension=substringAfterLast(bodyFileName,".");
    String mimeType=getMimeType(mapping);
    if (ContentTypes.determineIsText(extension,mimeType)) {
      TextFile bodyFile=filesFileSource.getTextFileNamed(bodyFileName);
      responseDefinitionBuilder.withBody(bodyFile.readContentsAsString());
    }
 else {
      BinaryFile bodyFile=filesFileSource.getBinaryFileNamed(bodyFileName);
      responseDefinitionBuilder.withBody(bodyFile.readContents());
    }
    mapping.setResponse(responseDefinitionBuilder.build());
  }
}
