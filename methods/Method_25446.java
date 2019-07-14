@Override public MethodClassMatcherImpl onClass(TypePredicate predicate){
  return new MethodClassMatcherImpl(this,predicate);
}
