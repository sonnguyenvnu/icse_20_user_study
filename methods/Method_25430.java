@Override public ConstructorClassMatcher forClass(String className){
  return new ConstructorClassMatcherImpl(this,TypePredicates.isExactType(className));
}
