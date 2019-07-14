@Override @Nullable public Choice<Unifier> visitAnnotation(AnnotationTree annotation,Unifier unifier){
  return getAnnotationType().unify(annotation.getAnnotationType(),unifier).thenChoose(unifications(getArguments(),annotation.getArguments()));
}
