public static List<Constraint> produceInitialConstraints(Method method,ASTArgumentList argList,List<Variable> variables){
  List<Constraint> result=new ArrayList<>();
  Type[] methodParameters=method.getGenericParameterTypes();
  TypeVariable<Method>[] methodTypeParameters=method.getTypeParameters();
  for (int i=0; i < methodParameters.length; i++) {
    int typeParamIndex=-1;
    if (methodParameters[i] instanceof TypeVariable) {
      typeParamIndex=JavaTypeDefinition.getGenericTypeIndex(methodTypeParameters,((TypeVariable<?>)methodParameters[i]).getName());
    }
    if (typeParamIndex != -1) {
      result.add(new Constraint(((TypeNode)argList.jjtGetChild(i)).getTypeDefinition(),variables.get(typeParamIndex),LOOSE_INVOCATION));
    }
  }
  return result;
}
