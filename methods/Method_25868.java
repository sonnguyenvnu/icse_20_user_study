private static String getMessage(MethodInvocationTree invocationTree,Type receiverType,Type argumentType,Type conflictingReceiverType,Type conflictingArgumentType,VisitorState state){
  TypeStringPair typeStringPair=new TypeStringPair(receiverType,argumentType);
  String baseMessage="Calling " + ASTHelpers.getSymbol(invocationTree).getSimpleName() + " on incompatible types " + typeStringPair.getReceiverTypeString() + " and " + typeStringPair.getArgumentTypeString();
  if (!state.getTypes().isSameType(receiverType,conflictingReceiverType)) {
    TypeStringPair conflictingTypes=new TypeStringPair(conflictingReceiverType,conflictingArgumentType);
    baseMessage+=". They are incompatible because " + conflictingTypes.getReceiverTypeString() + " and " + conflictingTypes.getArgumentTypeString() + " are incompatible.";
  }
  return baseMessage;
}
