public static MethodType parameterizeInvocation(JavaTypeDefinition context,Method method,ASTArgumentList argList){
  List<Variable> variables=new ArrayList<>();
  List<Bound> initialBounds=new ArrayList<>();
  produceInitialBounds(method,context,variables,initialBounds);
  List<JavaTypeDefinition> resolvedTypeParameters=TypeInferenceResolver.inferTypes(produceInitialConstraints(method,argList,variables),initialBounds,variables);
  if (resolvedTypeParameters == null) {
    return null;
  }
  return getTypeDefOfMethod(context,method,resolvedTypeParameters);
}
