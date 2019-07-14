/** 
 * Returns a proto source file for the schema. 
 */
public static String toProto(GraphQLSchema schema){
  ArrayList<String> messages=new ArrayList<>();
  for (  GraphQLType type : getAllTypes(schema)) {
    if (type instanceof GraphQLEnumType) {
      messages.add(toEnum((GraphQLEnumType)type));
    }
 else     if (type instanceof GraphQLObjectType) {
      messages.add(toMessage((GraphQLObjectType)type));
    }
  }
  return HEADER + Joiner.on("\n\n").join(messages);
}
