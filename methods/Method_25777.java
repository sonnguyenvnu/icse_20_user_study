private boolean catchVariableIsUsed(CatchTree c){
  VarSymbol sym=ASTHelpers.getSymbol(c.getParameter());
  boolean[] found={false};
  c.getBlock().accept(new TreeScanner<Void,Void>(){
    @Override public Void visitIdentifier(    IdentifierTree node,    Void aVoid){
      if (Objects.equals(sym,ASTHelpers.getSymbol(node))) {
        found[0]=true;
      }
      return super.visitIdentifier(node,aVoid);
    }
  }
,null);
  return found[0];
}
