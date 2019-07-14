/** 
 * Returns a GraphQLOutputType generated from a FieldDescriptor. 
 */
static GraphQLOutputType convertType(FieldDescriptor fieldDescriptor){
  final GraphQLOutputType type;
  if (fieldDescriptor.getType() == Type.MESSAGE) {
    type=getReference(fieldDescriptor.getMessageType());
  }
 else   if (fieldDescriptor.getType() == Type.GROUP) {
    type=getReference(fieldDescriptor.getMessageType());
  }
 else   if (fieldDescriptor.getType() == Type.ENUM) {
    type=getReference(fieldDescriptor.getEnumType());
  }
 else {
    type=TYPE_MAP.get(fieldDescriptor.getType());
  }
  if (type == null) {
    throw new RuntimeException("Unknown type: " + fieldDescriptor.getType());
  }
  if (fieldDescriptor.isRepeated()) {
    return new GraphQLList(type);
  }
 else {
    return type;
  }
}
