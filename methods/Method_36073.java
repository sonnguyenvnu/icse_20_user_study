/** 
 * Extracts body of the ResponseDefinition to a file written to the files source. Modifies the ResponseDefinition to point to the file in-place.
 * @param stubMapping Stub mapping to extract
 */
public void extractInPlace(StubMapping stubMapping){
  byte[] body=stubMapping.getResponse().getByteBody();
  HttpHeaders responseHeaders=stubMapping.getResponse().getHeaders();
  String extension=ContentTypes.determineFileExtension(stubMapping.getRequest().getUrl(),responseHeaders != null ? responseHeaders.getContentTypeHeader() : ContentTypeHeader.absent(),body);
  String bodyFileName=SafeNames.makeSafeFileName(stubMapping,extension);
  String noStringBody=null;
  byte[] noByteBody=null;
  stubMapping.setResponse(ResponseDefinitionBuilder.like(stubMapping.getResponse()).withBodyFile(bodyFileName).withBody(noStringBody).withBody(noByteBody).withBase64Body(null).build());
  fileSource.writeBinaryFile(bodyFileName,body);
}
