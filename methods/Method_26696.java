@Override @Nullable public Choice<Unifier> visitNewClass(NewClassTree newClass,@Nullable Unifier unifier){
  return unifyNullable(unifier,getEnclosingExpression(),newClass.getEnclosingExpression()).thenChoose(unifications(getTypeArguments(),newClass.getTypeArguments())).thenChoose(unifications(getIdentifier(),newClass.getIdentifier())).thenChoose(unifications(getClassBody(),newClass.getClassBody())).thenChoose(unifications(getArguments(),newClass.getArguments(),true));
}
