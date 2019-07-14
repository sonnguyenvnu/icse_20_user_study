@Override public MethodClassMatcherImpl onDescendantOf(Supplier<Type> classType){
  return new MethodClassMatcherImpl(this,TypePredicates.isDescendantOf(classType));
}
