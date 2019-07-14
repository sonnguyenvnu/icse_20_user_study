@Override public MethodClassMatcherImpl anyClass(){
  return new MethodClassMatcherImpl(this,TypePredicates.anyType());
}
