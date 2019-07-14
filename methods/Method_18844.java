/** 
 * Generate a delegate to the Spec that defines this onTrigger method. 
 */
static MethodSpec generateOnTriggerMethodDelegate(SpecModel specModel,SpecMethodModel<EventMethod,EventDeclarationModel> eventMethodModel){
  final String componentName=specModel.getComponentName();
  final MethodSpec.Builder methodSpec=MethodSpec.methodBuilder(eventMethodModel.name.toString()).addModifiers(Modifier.PRIVATE).returns(eventMethodModel.returnType).addParameter(ClassNames.EVENT_TRIGGER_TARGET,ABSTRACT_PARAM_NAME).addStatement("$L $L = ($L) $L",componentName,REF_VARIABLE_NAME,componentName,ABSTRACT_PARAM_NAME);
  final CodeBlock.Builder delegation=CodeBlock.builder();
  final String sourceDelegateAccessor=SpecModelUtils.getSpecAccessor(specModel);
  if (eventMethodModel.returnType.equals(TypeName.VOID)) {
    delegation.add("$L.$L(\n",sourceDelegateAccessor,eventMethodModel.name);
  }
 else {
    delegation.add("$T _result = ($T) $L.$L(\n",eventMethodModel.returnType,eventMethodModel.returnType,sourceDelegateAccessor,eventMethodModel.name);
  }
  delegation.indent();
  for (int i=0, size=eventMethodModel.methodParams.size(); i < size; i++) {
    final MethodParamModel methodParamModel=eventMethodModel.methodParams.get(i);
    if (MethodParamModelUtils.isAnnotatedWith(methodParamModel,FromTrigger.class) || MethodParamModelUtils.isAnnotatedWith(methodParamModel,Param.class)) {
      methodSpec.addParameter(methodParamModel.getTypeName(),methodParamModel.getName());
      delegation.add(methodParamModel.getName());
    }
 else     if (methodParamModel.getTypeName().equals(specModel.getContextClass())) {
      delegation.add("($T) $L.getScopedContext()",methodParamModel.getTypeName(),REF_VARIABLE_NAME);
    }
 else {
      delegation.add("($T) $L.$L",methodParamModel.getTypeName(),REF_VARIABLE_NAME,getImplAccessor(specModel,methodParamModel));
    }
    delegation.add((i < eventMethodModel.methodParams.size() - 1) ? ",\n" : ");\n");
  }
  delegation.unindent();
  methodSpec.addCode(delegation.build());
  if (!eventMethodModel.returnType.equals(TypeName.VOID)) {
    methodSpec.addStatement("return _result");
  }
  return methodSpec.build();
}
