@Override public Choice<Unifier> visitMemberSelect(MemberSelectTree fieldAccess,Unifier unifier){
  if (ASTHelpers.getSymbol(fieldAccess) != null) {
    return getIdentifier().unify(fieldAccess.getIdentifier(),unifier).thenChoose(unifications(getExpression(),fieldAccess.getExpression())).thenChoose(unifications(type(),ASTHelpers.getSymbol(fieldAccess).asType()));
  }
  return Choice.none();
}
