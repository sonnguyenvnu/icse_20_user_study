private MethodSpec.Builder baseMethod(@NonNull ClassName builderName){
  final MethodSpec.Builder method=MethodSpec.methodBuilder(getName()).addModifiers(modifiers).addParameters(parameters).addTypeVariables(typeVariables).addAnnotations(getAnnotations());
  if (javadoc != null) {
    method.addJavadoc(javadoc.replaceAll("(\n|^) ","$1"));
  }
  if (getType().equals(TypeName.VOID)) {
    method.returns(builderName).addAnnotation(Types.NON_NULL).addJavadoc("@return this instance\n");
  }
 else {
    method.returns(getType());
  }
  return method;
}
