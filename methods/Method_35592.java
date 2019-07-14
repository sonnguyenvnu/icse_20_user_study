public static <T>ResponseDefinitionBuilder okForEmptyJson(){
  return responseDefinition().withStatus(HTTP_OK).withBody("{}").withHeader("Content-Type","application/json");
}
