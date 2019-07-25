@Override public SNode coerce_(SNode subtype,IMatchingPattern pattern,boolean isWeak){
  SubTypingManagerNew subTyping=(SubTypingManagerNew)myTypeChecker.getSubtypingManager();
  return subTyping.coerceSubTypingNew(subtype,pattern,isWeak,null);
}
