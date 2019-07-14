@NonNull @Override public CodeBlock visitArray(@NonNull List<? extends AnnotationValue> values,Void v){
  ArrayTypeName arrayTypeName=(ArrayTypeName)type;
  if (arrayTypeName.componentType instanceof ParameterizedTypeName) {
    arrayTypeName=ArrayTypeName.of(((ParameterizedTypeName)arrayTypeName.componentType).rawType);
  }
  return CodeBlock.of("new $T{$L}",arrayTypeName,values.stream().map(value -> value.accept(this,null)).reduce((c1,c2) -> CodeBlock.builder().add(c1).add(", ").add(c2).build()).orElseGet(() -> CodeBlock.builder().build()));
}
