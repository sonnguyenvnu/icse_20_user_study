@Override public MethodNameMatcher withAnyName(){
  return new MethodNameMatcherImpl.Any(this);
}
