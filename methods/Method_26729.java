@Override public UExpression visitMemberSelect(MemberSelectTree tree,Void v){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym instanceof ClassSymbol) {
    return UClassIdent.create((ClassSymbol)sym);
  }
 else   if (sym.isStatic()) {
    ExpressionTree selected=tree.getExpression();
    checkState(ASTHelpers.getSymbol(selected) instanceof ClassSymbol,"Refaster cannot match static methods used on instances");
    return staticMember(sym);
  }
  return UMemberSelect.create(template(tree.getExpression()),tree.getIdentifier(),template(sym.type));
}
