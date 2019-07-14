private void writeToMappingAndBodyFile(Request request,Response response,RequestPattern requestPattern){
  String fileId=idGenerator.generate();
  byte[] body=bodyDecompressedIfRequired(response);
  String mappingFileName=UniqueFilenameGenerator.generate(request.getUrl(),"mapping",fileId);
  String bodyFileName=UniqueFilenameGenerator.generate(request.getUrl(),"body",fileId,ContentTypes.determineFileExtension(request.getUrl(),response.getHeaders().getContentTypeHeader(),body));
  ResponseDefinitionBuilder responseDefinitionBuilder=responseDefinition().withStatus(response.getStatus()).withBodyFile(bodyFileName);
  if (response.getHeaders().size() > 0) {
    responseDefinitionBuilder.withHeaders(withoutContentEncodingAndContentLength(response.getHeaders()));
  }
  ResponseDefinition responseToWrite=responseDefinitionBuilder.build();
  StubMapping mapping=new StubMapping(requestPattern,responseToWrite);
  mapping.setUuid(UUID.nameUUIDFromBytes(fileId.getBytes()));
  filesFileSource.writeBinaryFile(bodyFileName,body);
  mappingsFileSource.writeTextFile(mappingFileName,write(mapping));
}
