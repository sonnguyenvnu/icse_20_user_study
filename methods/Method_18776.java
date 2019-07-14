private static TypeName[] getBuilderGenericTypes(TypeName type,ClassName builderClass){
  if (builderClass.equals(ClassNames.COMPONENT_BUILDER) || builderClass.equals(ClassNames.SECTION_BUILDER)) {
    return new TypeName[]{WildcardTypeName.subtypeOf(TypeName.OBJECT)};
  }
 else {
    final TypeName typeParameter=type instanceof ParameterizedTypeName && !((ParameterizedTypeName)type).typeArguments.isEmpty() ? ((ParameterizedTypeName)type).typeArguments.get(0) : WildcardTypeName.subtypeOf(ClassNames.COMPONENT);
    return new TypeName[]{typeParameter};
  }
}
