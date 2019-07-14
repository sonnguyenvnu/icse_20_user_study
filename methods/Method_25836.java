private void checkForThis(ExpressionTree node,Name identifier,ClassSymbol thisClass,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(node);
  if (sym != null && !sym.isConstructor() && identifier.contentEquals("this") && thisClass.equals(sym.owner)) {
    state.reportMatch(describeMatch(node));
  }
}
