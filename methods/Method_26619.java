@Override @Nullable public Choice<Unifier> visitArrayType(ArrayTypeTree tree,@Nullable Unifier unifier){
  return getType().unify(tree.getType(),unifier);
}
