public static <T>ResponseDefinitionBuilder okForJson(T body){
  return responseDefinition().withStatus(HTTP_OK).withBody(Json.write(body)).withHeader("Content-Type","application/json");
}
