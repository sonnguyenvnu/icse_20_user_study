static CodeBlock generateStateUpdateDelegatingCall(SpecModel specModel,int methodIndex,SpecMethodModel<UpdateStateMethod,Void> updateStateMethod,boolean withTransition){
  final CodeBlock.Builder codeBlock=CodeBlock.builder();
  codeBlock.add("case $L:\n$>",methodIndex);
  final StringBuilder format=new StringBuilder();
  final List<Object> args=new LinkedList<>();
  if (withTransition) {
    format.append("$L = ");
    args.add(VAR_NAME_TRANSITION);
  }
  format.append("$N.$N(");
  args.add(SpecModelUtils.getSpecAccessor(specModel));
  args.add(updateStateMethod.name);
  int paramIndex=0;
  for (int i=0; i < updateStateMethod.methodParams.size(); i++) {
    if (i > 0) {
      format.append(", ");
    }
    final MethodParamModel methodParam=updateStateMethod.methodParams.get(i);
    if (MethodParamModelUtils.isAnnotatedWith(methodParam,Param.class)) {
      final TypeName paramTypeName=methodParam.getTypeName();
      if (!paramTypeName.equals(TypeName.OBJECT)) {
        format.append("($T) ");
        args.add(paramTypeName);
      }
      format.append("$L[$L]");
      args.add(VAR_NAME_PARAMS);
      args.add(paramIndex++);
    }
 else {
      final String name=methodParam.getName();
      codeBlock.addStatement("$L = new $T()",name,methodParam.getTypeName()).addStatement("$L.set(this.$L)",name,name);
      format.append("$L");
      args.add(name);
    }
  }
  format.append(')');
  codeBlock.addStatement(format.toString(),args.toArray());
  for (int i=0; i < updateStateMethod.methodParams.size(); i++) {
    final MethodParamModel methodParam=updateStateMethod.methodParams.get(i);
    if (MethodParamModelUtils.isAnnotatedWith(methodParam,Param.class)) {
      continue;
    }
    final String name=methodParam.getName();
    codeBlock.addStatement("this.$L = $L.get()",name,name);
  }
  codeBlock.addStatement("break$<");
  return codeBlock.build();
}
