private GraphQLType getFieldType(FieldDescriptor field){
  if (field.getType() == FieldDescriptor.Type.MESSAGE || field.getType() == FieldDescriptor.Type.GROUP) {
    return new GraphQLTypeReference(getReferenceName(field.getMessageType()));
  }
  if (field.getType() == FieldDescriptor.Type.ENUM) {
    return new GraphQLTypeReference(ProtoToGql.getReferenceName(field.getEnumType()));
  }
  GraphQLType type=ProtoToGql.convertType(field);
  if (type instanceof GraphQLList) {
    return ((GraphQLList)type).getWrappedType();
  }
  return type;
}
