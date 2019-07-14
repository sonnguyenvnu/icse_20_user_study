@Override public List<CaseInsensitiveKey> matchingHeaders(){
  if (optionSet.hasArgument(MATCH_HEADERS)) {
    String headerSpec=(String)optionSet.valueOf(MATCH_HEADERS);
    UnmodifiableIterator<String> headerKeys=Iterators.forArray(headerSpec.split(","));
    return ImmutableList.copyOf(Iterators.transform(headerKeys,TO_CASE_INSENSITIVE_KEYS));
  }
  return Collections.emptyList();
}
