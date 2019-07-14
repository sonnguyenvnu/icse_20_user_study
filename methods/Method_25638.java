static boolean isThrowingFunctionalInterface(VisitorState state,Type clazzType){
  return CLASSES_CONSIDERED_THROWING.stream().anyMatch(t -> ASTHelpers.isSubtype(clazzType,state.getTypeFromString(t),state));
}
