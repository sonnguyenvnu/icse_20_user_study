@Override public SNode coerce_(SNode subtype,IMatchingPattern pattern){
  SubTypingManagerNew subTyping=(SubTypingManagerNew)myTypeChecker.getSubtypingManager();
  return subTyping.coerceSubTypingNew(subtype,pattern,true,null);
}
