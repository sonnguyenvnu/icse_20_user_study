private static String toField(GraphQLFieldDefinition field){
  return String.format("  %s: %s;",field.getName(),toType(field.getType()));
}
