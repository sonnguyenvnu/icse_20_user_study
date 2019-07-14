@Override public MethodClassMatcherImpl onExactClass(Supplier<Type> classType){
  return new MethodClassMatcherImpl(this,TypePredicates.isExactType(classType));
}
