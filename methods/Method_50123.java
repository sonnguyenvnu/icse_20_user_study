/** 
 * Returns a proto source file for the schema. 
 */
public static String toTs(GraphQLSchema schema){
  ArrayList<String> messages=new ArrayList<>();
  for (  GraphQLType type : SchemaToProto.getAllTypes(schema)) {
    if (type instanceof GraphQLEnumType) {
      messages.add(toEnum((GraphQLEnumType)type));
    }
 else     if (type instanceof GraphQLObjectType) {
      messages.add(toMessage((GraphQLObjectType)type));
    }
  }
  return HEADER + Joiner.on("\n\n").join(messages);
}
