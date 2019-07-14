@Override public ConstructorClassMatcher forClass(Supplier<Type> classType){
  return new ConstructorClassMatcherImpl(this,TypePredicates.isExactType(classType));
}
