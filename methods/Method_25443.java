@Override public MethodNameMatcher withNameMatching(Pattern pattern){
  return new Regex(this,pattern);
}
