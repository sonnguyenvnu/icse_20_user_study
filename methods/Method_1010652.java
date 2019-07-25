@Override public SNode coerce_(SNode subtype,IMatchingPattern pattern,TypeCheckingContext typeCheckingContext){
  if (typeCheckingContext == null) {
    return coerce_(subtype,pattern);
  }
  return ((SubTypingManagerNew)TypeChecker.getInstance().getSubtypingManager()).coerceSubTypingNew(subtype,pattern,true,typeCheckingContext);
}
