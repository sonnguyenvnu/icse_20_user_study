private boolean areEquivalent(ASTCatchStatement st1,ASTCatchStatement st2){
  return hasSameSubTree(st1.getBlock(),st2.getBlock(),st1.getExceptionName(),st2.getExceptionName());
}
