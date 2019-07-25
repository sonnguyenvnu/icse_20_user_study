@Override public SNode coerce_(SNode subtype,IMatchingPattern pattern,boolean isWeak,TypeCheckingContext typeCheckingContext){
  if (typeCheckingContext == null) {
    return coerce_(subtype,pattern);
  }
  return ((SubTypingManagerNew)TypeChecker.getInstance().getSubtypingManager()).coerceSubTypingNew(subtype,pattern,isWeak,typeCheckingContext);
}
