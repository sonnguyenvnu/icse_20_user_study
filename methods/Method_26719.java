@Override protected Choice<Unifier> defaultAction(Tree node,Unifier unifier){
  Symbol symbol=ASTHelpers.getSymbol(node);
  if (symbol != null) {
    return classIdent().unify(symbol.getEnclosingElement(),unifier).thenChoose(unifications(getName(),symbol.getSimpleName())).thenChoose(unifications(memberType(),symbol.asType()));
  }
  return Choice.none();
}
