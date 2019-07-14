default MethodSpec.Builder baseSetter(ClassName builderName){
  MethodSpec.Builder builder=MethodSpec.methodBuilder(Strings.PREFIX_SETTER + WordUtils.capitalize(getName()));
  Collection<AnnotationSpec> annotations=new ArrayList<>(getAnnotations());
  boolean deprecated=annotations.contains(Types.DEPRECATED);
  if (deprecated) {
    annotations.remove(Types.DEPRECATED);
    builder.addAnnotation(Types.DEPRECATED);
  }
  return builder.addParameter(ParameterSpec.builder(getType(),getName()).addAnnotations(annotations).build()).varargs(getType() instanceof ArrayTypeName).addModifiers(Modifier.PUBLIC).addAnnotation(Types.NON_NULL).returns(builderName);
}
