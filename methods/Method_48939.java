public static <R>List<R> processIntersectingRetrievals(List<IndexCall<R>> retrievals,final int limit){
  Preconditions.checkArgument(!retrievals.isEmpty());
  Preconditions.checkArgument(limit >= 0,"Invalid limit: %s",limit);
  List<R> results;
  final int multiplier=Math.min(16,(int)Math.pow(2,retrievals.size() - 1));
  int subLimit=Integer.MAX_VALUE;
  if (Integer.MAX_VALUE / multiplier >= limit)   subLimit=limit * multiplier;
  boolean exhaustedResults;
  do {
    exhaustedResults=true;
    results=null;
    for (    final IndexCall<R> call : retrievals) {
      Collection<R> subResult;
      try {
        subResult=call.call(subLimit);
      }
 catch (      final Exception e) {
        throw new JanusGraphException("Could not process individual retrieval call ",e);
      }
      if (subResult.size() >= subLimit)       exhaustedResults=false;
      if (results == null) {
        results=Lists.newArrayList(subResult);
      }
 else {
        final Set<R> subResultSet=ImmutableSet.copyOf(subResult);
        results.removeIf(o -> !subResultSet.contains(o));
      }
    }
    subLimit=(int)Math.min(Integer.MAX_VALUE - 1,Math.max(Math.pow(subLimit,1.5),(subLimit + 1) * 2));
  }
 while (results != null && results.size() < limit && !exhaustedResults);
  return results;
}
