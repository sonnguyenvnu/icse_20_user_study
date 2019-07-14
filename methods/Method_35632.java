public static ResponseDefinitionBuilder okForContentType(String contentType,String body){
  return aResponse().withStatus(200).withHeader(CONTENT_TYPE,contentType).withBody(body);
}
