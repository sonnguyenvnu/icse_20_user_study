/** 
 * Generate a delegate to the Spec that defines this component. 
 */
private static MethodSpec generateDelegate(SpecModel specModel,DelegateMethodDescription methodDescription,SpecMethodModel<DelegateMethod,Void> delegateMethod,EnumSet<RunMode> runMode){
  final MethodSpec.Builder methodSpec=MethodSpec.methodBuilder(methodDescription.name).addModifiers(methodDescription.accessType).returns(methodDescription.returnType);
  for (  AnnotationSpec annotation : methodDescription.annotations) {
    methodSpec.addAnnotation(annotation);
  }
  for (int i=0, size=methodDescription.definedParameterTypes.size(); i < size; i++) {
    methodSpec.addParameter(methodDescription.definedParameterTypes.get(i),delegateMethod.methodParams.get(i).getName());
  }
  final boolean methodUsesDiffs=methodDescription.optionalParameterTypes.contains(DIFF_PROP) || methodDescription.optionalParameterTypes.contains(DIFF_STATE);
  final String componentName=specModel.getComponentName();
  for (  TypeName exception : methodDescription.exceptions) {
    methodSpec.addException(exception);
  }
  if (methodUsesDiffs) {
    methodSpec.addParameter(specModel.getComponentClass(),"_prevAbstractImpl");
    methodSpec.addParameter(specModel.getComponentClass(),"_nextAbstractImpl");
    methodSpec.addStatement("$L _prevImpl = ($L) _prevAbstractImpl",componentName,componentName);
    methodSpec.addStatement("$L _nextImpl = ($L) _nextAbstractImpl",componentName,componentName);
  }
  if (!methodDescription.returnType.equals(TypeName.VOID)) {
    methodSpec.addStatement("$T _result",methodDescription.returnType);
  }
  methodSpec.addCode(getDelegationCode(specModel,delegateMethod,methodDescription,runMode));
  if (delegateMethod.name.toString().equals("onCreateLayout") || delegateMethod.name.toString().equals("onPrepare")) {
    SpecMethodModel<EventMethod,Void> registerRangesModel=specModel.getWorkingRangeRegisterMethod();
    if (registerRangesModel != null) {
      CodeBlock.Builder registerDelegation=CodeBlock.builder().add("$L.$L(\n",SpecModelUtils.getSpecAccessor(specModel),registerRangesModel.name);
      registerDelegation.indent();
      for (int i=0, size=registerRangesModel.methodParams.size(); i < size; i++) {
        final MethodParamModel methodParamModel=registerRangesModel.methodParams.get(i);
        registerDelegation.add("($T) $L",methodParamModel.getTypeName(),getImplAccessor(specModel,methodParamModel));
        registerDelegation.add((i < registerRangesModel.methodParams.size() - 1) ? ",\n" : ");\n");
      }
      registerDelegation.unindent();
      methodSpec.addCode(registerDelegation.build());
    }
  }
  if (!methodDescription.returnType.equals(TypeName.VOID)) {
    methodSpec.addStatement("return _result");
  }
  return methodSpec.build();
}
