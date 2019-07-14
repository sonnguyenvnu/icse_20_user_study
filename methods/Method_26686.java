@Override @Nullable public Choice<Unifier> visitMethod(MethodTree decl,@Nullable Unifier unifier){
  return getName().unify(decl.getName(),unifier).thenChoose(unifications(getReturnType(),decl.getReturnType())).thenChoose(unifications(getParameters(),decl.getParameters())).thenChoose(unifications(getThrows(),decl.getThrows())).thenChoose(unifications(getBody(),decl.getBody()));
}
