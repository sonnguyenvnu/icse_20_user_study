public static ResponseDefinition badRequest(Errors errors){
  return ResponseDefinitionBuilder.responseDefinition().withStatus(422).withHeader(CONTENT_TYPE,"application/json").withBody(Json.write(errors)).build();
}
