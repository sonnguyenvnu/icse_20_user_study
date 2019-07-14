@Override @Nullable public Choice<Unifier> visitParameterizedType(ParameterizedTypeTree typeApply,@Nullable Unifier unifier){
  Choice<Unifier> choice=getType().unify(typeApply.getType(),unifier);
  if (getTypeArguments().isEmpty()) {
    return choice.condition(typeApply.getTypeArguments() != null);
  }
 else {
    return choice.thenChoose(unifications(getTypeArguments(),typeApply.getTypeArguments()));
  }
}
