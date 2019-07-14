@Override public MethodMatchers.MethodClassMatcher anyClass(){
  return new MethodClassMatcherImpl(this,TypePredicates.anyType());
}
