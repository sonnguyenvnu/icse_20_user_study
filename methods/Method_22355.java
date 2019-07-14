public void addDelegateCall(@NonNull String methodName){
  statements.add(CodeBlock.builder().addStatement("$L.$L()",Strings.FIELD_DELEGATE,methodName).build());
}
