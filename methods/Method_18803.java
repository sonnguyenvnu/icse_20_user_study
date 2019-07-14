private static List<MethodParamModel> findComponentsInImpl(SpecModel specModel){
  final List<MethodParamModel> componentsInImpl=new ArrayList<>();
  for (  PropModel prop : specModel.getProps()) {
    TypeName typeName=prop.getTypeName();
    if (typeName instanceof ParameterizedTypeName) {
      typeName=((ParameterizedTypeName)typeName).rawType;
    }
    if (typeName.equals(ClassNames.COMPONENT) || typeName.equals(ClassNames.SECTION)) {
      componentsInImpl.add(prop);
    }
  }
  return componentsInImpl;
}
