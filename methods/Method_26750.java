@Override public Choice<Unifier> visitTypeCast(TypeCastTree cast,Unifier unifier){
  return getType().unify(cast.getType(),unifier).thenChoose(unifications(getExpression(),cast.getExpression()));
}
