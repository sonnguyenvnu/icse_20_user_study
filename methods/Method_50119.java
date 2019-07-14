@SuppressWarnings("JdkObsolete") static Set<GraphQLType> getAllTypes(GraphQLSchema schema){
  LinkedHashSet<GraphQLType> types=new LinkedHashSet<>();
  LinkedList<GraphQLObjectType> loop=new LinkedList<>();
  loop.add(schema.getQueryType());
  types.add(schema.getQueryType());
  while (!loop.isEmpty()) {
    for (    GraphQLFieldDefinition field : loop.pop().getFieldDefinitions()) {
      GraphQLType type=field.getType();
      if (type instanceof GraphQLList) {
        type=((GraphQLList)type).getWrappedType();
      }
      if (!types.contains(type)) {
        if (type instanceof GraphQLEnumType) {
          types.add(field.getType());
        }
        if (type instanceof GraphQLObjectType) {
          types.add(type);
          loop.add((GraphQLObjectType)type);
        }
      }
    }
  }
  return types;
}
