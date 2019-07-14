public static ResponseDefinitionBuilder okJson(String body){
  return okForContentType("application/json",body);
}
