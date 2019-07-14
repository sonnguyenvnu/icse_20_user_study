private static boolean isInherited(VisitorState state,String annotationName){
  Symbol annotationSym=state.getSymbolFromString(annotationName);
  if (annotationSym == null) {
    return false;
  }
  try {
    annotationSym.complete();
  }
 catch (  CompletionFailure e) {
  }
  Symbol inheritedSym=state.getSymtab().inheritedType.tsym;
  return annotationSym.attribute(inheritedSym) != null;
}
