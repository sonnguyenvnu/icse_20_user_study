public static TypeCompatibilityReport compatibilityOfTypes(Type receiverType,Type argumentType,VisitorState state){
  return compatibilityOfTypes(receiverType,argumentType,typeSet(state),typeSet(state),state);
}
