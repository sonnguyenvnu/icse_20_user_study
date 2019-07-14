@Override @Nullable public Choice<Unifier> visitInstanceOf(InstanceOfTree instanceOf,@Nullable Unifier unifier){
  return getExpression().unify(instanceOf.getExpression(),unifier).thenChoose(unifications(getType(),instanceOf.getType()));
}
