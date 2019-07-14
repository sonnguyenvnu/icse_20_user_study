@Override public MethodSignatureMatcher withSignature(String signature){
  return new MethodSignatureMatcherImpl(this,signature);
}
