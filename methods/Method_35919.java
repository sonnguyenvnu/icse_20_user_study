public static ResponseDefinition redirectTo(String path){
  return new ResponseDefinitionBuilder().withHeader("Location",path).withStatus(HTTP_MOVED_TEMP).build();
}
