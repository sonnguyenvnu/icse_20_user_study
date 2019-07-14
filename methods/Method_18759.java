private static TypeName getBuilderType(SpecModel specModel){
  return (specModel.getTypeVariables().isEmpty()) ? BUILDER_CLASS_NAME : ParameterizedTypeName.get(BUILDER_CLASS_NAME,specModel.getTypeVariables().toArray(new TypeName[specModel.getTypeVariables().size()]));
}
