private static TypeSpecDataHolder generateOptionalField(MethodParamModel optionalField){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  if (optionalField == null) {
    return typeSpecDataHolder.build();
  }
  typeSpecDataHolder.addField(FieldSpec.builder(optionalField.getTypeName(),optionalField.getName()).build());
  return typeSpecDataHolder.build();
}
