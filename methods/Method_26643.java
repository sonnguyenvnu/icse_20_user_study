@Override public Choice<Unifier> visitClassType(ClassType classType,Unifier unifier){
  return fullyQualifiedClass().unify(classType.tsym.getQualifiedName(),unifier).thenChoose(unifications(typeArguments(),classType.getTypeArguments()));
}
