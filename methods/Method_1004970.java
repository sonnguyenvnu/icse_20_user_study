@Override public void init(final Iterable matchCandidates){
  if (matchCandidates == null) {
    throw new IllegalArgumentException(NULL_MATCH_CANDIDATES_ERROR_MESSAGE);
  }
  for (  final Object matchCandidate : matchCandidates) {
    Object key=secondKeyFunction.apply(matchCandidate);
    List list=keyedMatchCandidates.get(key);
    if (list == null) {
      list=new ArrayList();
    }
    list.add(matchCandidate);
    keyedMatchCandidates.put(key,list);
  }
}
