private static TypeCompatibilityReport matchesSubtypeAndIsGenericMismatch(Type receiverType,Type argumentType,Type superType,Set<Type> previousReceiverTypes,Set<Type> previousArgumentTypes,VisitorState state){
  List<Type> receiverTypes=typeArgsAsSuper(receiverType,superType,state);
  List<Type> argumentTypes=typeArgsAsSuper(argumentType,superType,state);
  return Streams.zip(receiverTypes.stream(),argumentTypes.stream(),TypePair::new).filter(tp -> !(previousReceiverTypes.contains(tp.receiver) || ASTHelpers.isSameType(tp.receiver,receiverType,state) || previousArgumentTypes.contains(tp.argument) || ASTHelpers.isSameType(tp.argument,argumentType,state))).map(types -> {
    Set<Type> nextReceiverTypes=typeSet(state);
    nextReceiverTypes.addAll(previousReceiverTypes);
    nextReceiverTypes.add(receiverType);
    Set<Type> nextArgumentTypes=typeSet(state);
    nextArgumentTypes.addAll(previousArgumentTypes);
    nextArgumentTypes.add(argumentType);
    return compatibilityOfTypes(types.receiver,types.argument,nextReceiverTypes,nextArgumentTypes,state);
  }
).filter(tcr -> !tcr.compatible()).findFirst().orElse(TypeCompatibilityReport.createCompatibleReport());
}
