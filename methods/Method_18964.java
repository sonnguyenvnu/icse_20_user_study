static List<TypeVariableName> getTypeVariables(ExecutableElement method){
  final List<TypeVariableName> typeVariables=new ArrayList<>();
  for (  TypeParameterElement typeParameterElement : method.getTypeParameters()) {
    typeVariables.add(TypeVariableName.get(typeParameterElement.getSimpleName().toString(),getBounds(typeParameterElement)));
  }
  return typeVariables;
}
