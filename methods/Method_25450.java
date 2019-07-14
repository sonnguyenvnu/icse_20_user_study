@Override public MethodClassMatcherImpl onClass(Supplier<Type> classType){
  return new MethodClassMatcherImpl(this,TypePredicates.isExactType(classType));
}
