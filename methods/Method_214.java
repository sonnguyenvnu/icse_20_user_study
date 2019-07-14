JavaFile brewJava(int sdk,boolean debuggable){
  TypeSpec bindingConfiguration=createType(sdk,debuggable);
  return JavaFile.builder(bindingClassName.packageName(),bindingConfiguration).addFileComment("Generated code from Butter Knife. Do not modify!").build();
}
