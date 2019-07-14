private static CodeBlock getDelegationMethod(SpecModel specModel,CharSequence methodName,TypeName returnType,ImmutableList<ParamTypeAndName> methodParams){
  final CodeBlock.Builder delegation=CodeBlock.builder();
  final String sourceDelegateAccessor=SpecModelUtils.getSpecAccessor(specModel);
  if (returnType.equals(TypeName.VOID)) {
    delegation.add("$L.$L(\n",sourceDelegateAccessor,methodName);
  }
 else {
    delegation.add("_result = ($T) $L.$L(\n",returnType,sourceDelegateAccessor,methodName);
  }
  delegation.indent();
  for (int i=0; i < methodParams.size(); i++) {
    delegation.add("($T) $L",methodParams.get(i).type,methodParams.get(i).name);
    if (i < methodParams.size() - 1) {
      delegation.add(",\n");
    }
  }
  delegation.add(");\n");
  delegation.unindent();
  return delegation.build();
}
