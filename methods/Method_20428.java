private static boolean areParamsTheSame(ExecutableElement method1,MethodSpec method2,Types types,Elements elements){
  List<? extends VariableElement> params1=method1.getParameters();
  List<ParameterSpec> params2=method2.parameters;
  if (params1.size() != params2.size()) {
    return false;
  }
  for (int i=0; i < params1.size(); i++) {
    VariableElement param1=params1.get(i);
    ParameterSpec param2=params2.get(i);
    TypeMirror param1Type=types.erasure(param1.asType());
    TypeMirror param2Type=types.erasure(KotlinUtilsKt.getTypeMirror(param2.type.toString(),elements));
    if (param1.asType().getKind() == TypeKind.TYPEVAR) {
      if (!types.isAssignable(param2Type,param1Type)) {
        return false;
      }
    }
 else     if (!param1Type.toString().equals(param2Type.toString())) {
      return false;
    }
  }
  return true;
}
