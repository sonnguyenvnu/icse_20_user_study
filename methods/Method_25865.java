private static TypeCompatibilityReport leastUpperBoundGenericMismatch(Type receiverType,Type argumentType,Set<Type> previousReceiverTypes,Set<Type> previousArgumentTypes,VisitorState state){
  Type lub=state.getTypes().lub(argumentType,receiverType);
  if (lub.getTag().equals(TypeTag.BOT) || lub.getTag().equals(TypeTag.ERROR)) {
    return TypeCompatibilityReport.createCompatibleReport();
  }
  TypeCompatibilityReport compatibilityReport=matchesSubtypeAndIsGenericMismatch(receiverType,argumentType,lub,previousReceiverTypes,previousArgumentTypes,state);
  if (!compatibilityReport.compatible()) {
    return compatibilityReport;
  }
  Type collectionType=state.getTypeFromString("java.util.Collection");
  if (ASTHelpers.isSameType(lub,collectionType,state) && !ASTHelpers.isSameType(receiverType,collectionType,state) && !ASTHelpers.isSameType(argumentType,collectionType,state)) {
    return TypeCompatibilityReport.incompatible(receiverType,argumentType);
  }
  return compatibilityReport;
}
