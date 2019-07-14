@Override public String dysfunctionReason(){
  return hasPrefixesOrSuffixes() ? null : "No prefixes or suffixes specified";
}
