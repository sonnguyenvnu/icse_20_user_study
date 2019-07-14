private void matchMethodDeclaration(JavaNameOccurrence occurrence,Set<MethodNameDeclaration> methodDeclarations,final boolean hasAuxclasspath,Set<NameDeclaration> result){
  for (  MethodNameDeclaration mnd : methodDeclarations) {
    if (mnd.getImage().equals(occurrence.getImage())) {
      List<TypedNameDeclaration> parameterTypes=determineParameterTypes(mnd);
      List<TypedNameDeclaration> argumentTypes=determineArgumentTypes(occurrence,parameterTypes);
      if (!mnd.isVarargs() && occurrence.getArgumentCount() == mnd.getParameterCount() && (!hasAuxclasspath || parameterTypes.equals(argumentTypes))) {
        result.add(mnd);
      }
 else       if (mnd.isVarargs()) {
        int varArgIndex=parameterTypes.size() - 1;
        TypedNameDeclaration varArgType=parameterTypes.get(varArgIndex);
        if ((varArgIndex == 0 || argumentTypes.size() >= varArgIndex) && (!hasAuxclasspath || parameterTypes.subList(0,varArgIndex).equals(argumentTypes.subList(0,varArgIndex)))) {
          if (!hasAuxclasspath) {
            result.add(mnd);
            continue;
          }
          boolean sameType=true;
          for (int i=varArgIndex; i < argumentTypes.size(); i++) {
            if (!varArgType.equals(argumentTypes.get(i))) {
              sameType=false;
              break;
            }
          }
          if (sameType) {
            result.add(mnd);
          }
        }
      }
    }
  }
}
