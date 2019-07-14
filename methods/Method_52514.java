public static boolean checkSubtypeability(MethodType method,MethodType subtypeableMethod){
  List<JavaTypeDefinition> subtypeableParams=subtypeableMethod.getParameterTypes();
  List<JavaTypeDefinition> methodParams=method.getParameterTypes();
  if (!method.getMethod().isVarArgs() || !subtypeableMethod.getMethod().isVarArgs()) {
    for (int index=0; index < subtypeableParams.size(); ++index) {
      if (!isSubtypeable(methodParams.get(index),subtypeableParams.get(index))) {
        return false;
      }
    }
  }
 else {
    final int maxSize=Math.max(subtypeableParams.size(),methodParams.size());
    for (int index=0; index < maxSize; ++index) {
      if (!isSubtypeable(method.getArgTypeIncludingVararg(index),subtypeableMethod.getArgTypeIncludingVararg(index))) {
        return false;
      }
    }
  }
  return true;
}
