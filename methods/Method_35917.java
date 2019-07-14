public static <T>ResponseDefinition okForJson(T body){
  return ResponseDefinitionBuilder.okForJson(body).build();
}
