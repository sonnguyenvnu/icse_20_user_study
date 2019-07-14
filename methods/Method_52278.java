public boolean hasDissallowedTerms(){
  List<String> terms=getProperty(DISSALLOWED_TERMS_DESCRIPTOR);
  return !terms.isEmpty();
}
