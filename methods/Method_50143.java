GraphQLType getInputType(Descriptor descriptor){
  GraphQLInputObjectType.Builder builder=GraphQLInputObjectType.newInputObject().name(getReferenceName(descriptor));
  if (descriptor.getFields().isEmpty()) {
    builder.field(STATIC_FIELD);
  }
  for (  FieldDescriptor field : descriptor.getFields()) {
    GraphQLType fieldType=getFieldType(field);
    GraphQLInputObjectField.Builder inputBuilder=GraphQLInputObjectField.newInputObjectField().name(getFieldName(field));
    if (field.isRepeated()) {
      inputBuilder.type(new GraphQLList(fieldType));
    }
 else {
      inputBuilder.type((GraphQLInputType)fieldType);
    }
    inputBuilder.description(DescriptorSet.COMMENTS.get(field.getFullName()));
    builder.field(inputBuilder.build());
  }
  builder.description(DescriptorSet.COMMENTS.get(descriptor.getFullName()));
  return builder.build();
}
