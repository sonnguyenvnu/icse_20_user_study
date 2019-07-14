static GraphQLEnumType convert(EnumDescriptor descriptor){
  GraphQLEnumType.Builder builder=GraphQLEnumType.newEnum().name(getReferenceName(descriptor));
  for (  EnumValueDescriptor value : descriptor.getValues()) {
    builder.value(value.getName(),value.getName(),DescriptorSet.COMMENTS.get(value.getFullName()),value.getOptions().getDeprecated() ? "deprecated in proto" : null);
  }
  return builder.build();
}
