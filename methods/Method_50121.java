private static String toMessage(GraphQLObjectType type){
  ArrayList<String> fields=new ArrayList<>();
  fields.add(getJspb(type));
  int i=1;
  for (  GraphQLFieldDefinition field : type.getFieldDefinitions()) {
    if (field.getName().equals("_")) {
      continue;
    }
    fields.add(String.format("%s = %d;",toField(field),i));
    i++;
  }
  return String.format("message %s {\n%s\n}",type.getName(),Joiner.on("\n").join(fields));
}
