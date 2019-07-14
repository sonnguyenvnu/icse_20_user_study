private Description checkLostType(MethodInvocationTree tree,VisitorState state){
  Type futureType=state.getTypeFromString("java.util.concurrent.Future");
  if (futureType == null) {
    return Description.NO_MATCH;
  }
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  Type returnType=ASTHelpers.getResultType(tree);
  Type returnedFutureType=state.getTypes().asSuper(returnType,futureType.tsym);
  if (returnedFutureType != null && !returnedFutureType.hasTag(TypeTag.ERROR) && !returnedFutureType.isRaw()) {
    if (ASTHelpers.isSubtype(ASTHelpers.getUpperBound(returnedFutureType.getTypeArguments().get(0),state.getTypes()),futureType,state)) {
      return buildDescription(tree).setMessage(String.format("Method returns a nested type, %s",returnType)).build();
    }
    Type methodReturnType=sym.getReturnType();
    List<TypeVariableSymbol> typeParameters=sym.getTypeParameters();
    Set<TypeVariableSymbol> returnTypeChoosing=new HashSet<>();
    for (    TypeVariableSymbol tvs : typeParameters) {
      Queue<TypeVariableSymbol> queue=new ArrayDeque<>();
      queue.add(tvs);
      while (!queue.isEmpty()) {
        TypeVariableSymbol currentTypeParam=queue.poll();
        for (        Type typeParam : methodReturnType.getTypeArguments()) {
          if (typeParam.tsym == currentTypeParam) {
            returnTypeChoosing.add(tvs);
          }
        }
        for (        Type toAdd : currentTypeParam.getBounds()) {
          if (toAdd.tsym instanceof TypeVariableSymbol) {
            queue.add((TypeVariableSymbol)toAdd.tsym);
          }
        }
      }
    }
    if (!returnTypeChoosing.isEmpty()) {
      Multimap<TypeVariableSymbol,TypeInfo> resolved=getResolvedGenerics(tree);
      for (      TypeVariableSymbol returnTypeChoosingSymbol : returnTypeChoosing) {
        Collection<TypeInfo> types=resolved.get(returnTypeChoosingSymbol);
        for (        TypeInfo type : types) {
          if (ASTHelpers.isSubtype(type.resolvedVariableType,futureType,state)) {
            return buildDescription(type.tree).setMessage(String.format("Invocation produces a nested type - Type variable %s, as part of return " + "type %s resolved to %s.",returnTypeChoosingSymbol,methodReturnType,type.resolvedVariableType)).build();
          }
        }
      }
    }
  }
  if (allOf(allOf(parentNode(FutureReturnValueIgnored::isObjectReturningLambdaExpression),not(AbstractReturnValueIgnored::expectedExceptionTest)),specializedMatcher(),not((t,s) -> ASTHelpers.isVoidType(ASTHelpers.getType(t),s))).matches(tree,state)) {
    return describe(tree,state);
  }
  return Description.NO_MATCH;
}
