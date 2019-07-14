private static TypeCompatibilityReport compatibilityOfTypes(Type receiverType,Type argumentType,Set<Type> previousReceiverTypes,Set<Type> previousArgumentTypes,VisitorState state){
  if (receiverType == null || argumentType == null) {
    return TypeCompatibilityReport.createCompatibleReport();
  }
  if (receiverType.isPrimitive() && argumentType.isPrimitive() && !ASTHelpers.isSameType(receiverType,argumentType,state)) {
    return TypeCompatibilityReport.incompatible(receiverType,argumentType);
  }
  if (ASTHelpers.isCastable(argumentType,receiverType,state)) {
    return leastUpperBoundGenericMismatch(receiverType,argumentType,previousReceiverTypes,previousArgumentTypes,state);
  }
  Types types=state.getTypes();
  Predicate<MethodSymbol> equalsPredicate=methodSymbol -> !methodSymbol.isStatic() && ((methodSymbol.flags() & Flags.SYNTHETIC) == 0) && types.isSameType(methodSymbol.getReturnType(),state.getSymtab().booleanType) && methodSymbol.getParameters().size() == 1 && types.isSameType(methodSymbol.getParameters().get(0).type,state.getSymtab().objectType);
  Set<MethodSymbol> overridesOfEquals=ASTHelpers.findMatchingMethods(state.getName("equals"),equalsPredicate,receiverType,types);
  Symbol argumentClass=ASTHelpers.getUpperBound(argumentType,state.getTypes()).tsym;
  for (  MethodSymbol method : overridesOfEquals) {
    ClassSymbol methodClass=method.enclClass();
    if (argumentClass.isSubClass(methodClass,types) && !methodClass.equals(state.getSymtab().objectType.tsym) && !methodClass.equals(state.getSymtab().enumSym)) {
      return leastUpperBoundGenericMismatch(receiverType,argumentType,previousReceiverTypes,previousArgumentTypes,state);
    }
  }
  return TypeCompatibilityReport.incompatible(receiverType,argumentType);
}
