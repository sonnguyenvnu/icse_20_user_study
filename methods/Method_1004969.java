@Override public List matching(final Object testObject){
  if (matchCandidates == null) {
    throw new IllegalArgumentException(NULL_MATCH_CANDIDATES_ERROR_MESSAGE);
  }
  List matches=new ArrayList<>();
  for (  final Object entry : matchCandidates) {
    if (elementJoinComparator.test((Element)entry,(Element)testObject)) {
      matches.add(((Element)entry).shallowClone());
    }
  }
  return matches;
}
