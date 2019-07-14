@NonNull @Override public CodeBlock visitEnumConstant(@NonNull VariableElement c,Void v){
  return CodeBlock.of("$T.$L",c.asType(),c.getSimpleName());
}
