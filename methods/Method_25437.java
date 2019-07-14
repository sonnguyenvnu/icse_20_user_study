@Override public MethodClassMatcherImpl onDescendantOfAny(Iterable<String> classTypes){
  return new MethodClassMatcherImpl(this,TypePredicates.isDescendantOfAny(classTypes));
}
