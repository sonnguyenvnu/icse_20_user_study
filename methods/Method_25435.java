@Override public MethodClassMatcherImpl onDescendantOf(String className){
  return new MethodClassMatcherImpl(this,TypePredicates.isDescendantOf(className));
}
