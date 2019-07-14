@Override public Choice<Unifier> visitIdentifier(final IdentifierTree ident,Unifier unifier){
  Symbol sym=ASTHelpers.getSymbol(ident);
  if (sym != null && sym.owner.type != null) {
    JCExpression thisIdent=unifier.thisExpression(sym.owner.type);
    return getIdentifier().unify(ident.getName(),unifier).thenChoose(unifications(getExpression(),thisIdent)).thenChoose(unifications(type(),sym.asType()));
  }
  return Choice.none();
}
