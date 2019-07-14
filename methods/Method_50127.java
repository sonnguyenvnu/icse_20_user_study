private static String toType(GraphQLType type){
  if (type instanceof GraphQLList) {
    return toType(((GraphQLList)type).getWrappedType()) + "[]";
  }
 else   if (type instanceof GraphQLObjectType) {
    return type.getName();
  }
 else   if (type instanceof GraphQLEnumType) {
    return type.getName() + "Enum";
  }
 else {
    return TYPE_MAP.get(type.getName());
  }
}
