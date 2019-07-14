private boolean isNotAMethodReference(NameOccurrence qualifier){
  return isNotA(qualifier,ASTMethodReference.class);
}
