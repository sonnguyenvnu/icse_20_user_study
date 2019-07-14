private static String toMessage(GraphQLObjectType type){
  String fields=type.getFieldDefinitions().stream().filter(field -> !field.getName().equals("_")).map(field -> toField(field)).collect(Collectors.joining("\n"));
  return String.format("export interface %s {\n%s\n}",type.getName(),fields);
}
