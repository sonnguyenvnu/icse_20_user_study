@Override public void addToBuilder(@NonNull TypeSpec.Builder builder,@NonNull ClassName builderName,@NonNull CodeBlock.Builder constructorAlways,@NonNull CodeBlock.Builder constructorWhenAnnotationPresent,CodeBlock.Builder constructorWhenAnnotationMissing){
  final MethodSpec.Builder method=baseMethod(builderName);
  if (getType().equals(TypeName.VOID)) {
    method.addStatement("$L.$L($L)",Strings.FIELD_DELEGATE,getName(),parameters.stream().map(p -> p.name).collect(Collectors.joining(", "))).addStatement("return this");
  }
 else {
    method.addStatement("return $L.$L($L)",Strings.FIELD_DELEGATE,getName(),parameters.stream().map(p -> p.name).collect(Collectors.joining(", ")));
  }
  builder.addMethod(method.build());
}
