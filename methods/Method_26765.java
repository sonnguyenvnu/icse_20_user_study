@Override public Choice<Unifier> visitWildcardType(WildcardType wildcard,Unifier unifier){
  return Choice.condition(boundKind().equals(wildcard.kind),unifier).thenChoose(unifications(bound(),wildcard.type));
}
