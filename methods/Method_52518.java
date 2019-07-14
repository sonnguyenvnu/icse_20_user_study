public static void produceInitialBounds(Method method,JavaTypeDefinition context,List<Variable> variables,List<Bound> initialBounds){
  TypeVariable<Method>[] typeVariables=method.getTypeParameters();
  variables.clear();
  for (int i=0; i < typeVariables.length; ++i) {
    variables.add(new Variable());
  }
  for (int currVarIndex=0; currVarIndex < typeVariables.length; ++currVarIndex) {
    Type[] bounds=typeVariables[currVarIndex].getBounds();
    boolean currVarHasNoProperUpperBound=true;
    for (    Type bound : bounds) {
      int boundVarIndex=-1;
      if (bound instanceof TypeVariable) {
        boundVarIndex=JavaTypeDefinition.getGenericTypeIndex(typeVariables,((TypeVariable<?>)bound).getName());
      }
      if (boundVarIndex != -1) {
        initialBounds.add(new Bound(variables.get(currVarIndex),variables.get(boundVarIndex),SUBTYPE));
      }
 else {
        currVarHasNoProperUpperBound=false;
        initialBounds.add(new Bound(variables.get(currVarIndex),context.resolveTypeDefinition(bound),SUBTYPE));
      }
    }
    if (currVarHasNoProperUpperBound) {
      initialBounds.add(new Bound(variables.get(currVarIndex),JavaTypeDefinition.forClass(Object.class),SUBTYPE));
    }
  }
}
