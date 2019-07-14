@Override public MethodClassMatcherImpl onClassAny(Iterable<String> classNames){
  return new MethodClassMatcherImpl(this,TypePredicates.isExactTypeAny(classNames));
}
