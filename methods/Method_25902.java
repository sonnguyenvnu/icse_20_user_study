private static boolean maybeFunctionalInterface(Type type,Types types,VisitorState state){
  try {
    return types.isFunctionalInterface(type);
  }
 catch (  CompletionFailure e) {
    Check.instance(state.context).completionError((DiagnosticPosition)state.getPath().getLeaf(),e);
    return false;
  }
}
