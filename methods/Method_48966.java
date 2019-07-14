private void constructSliceQueries(PropertyKey[] extendedSortKey,EdgeSerializer.TypedInterval[] sortKeyConstraints,int position,InternalRelationType bestCandidate,Direction direction,Map<RelationType,Interval> intervalConstraints,int sliceLimit,boolean isIntervalFittedConditions,boolean bestCandidateSupportsOrder,List<BackendQueryHolder<SliceQuery>> queries){
  if (position < extendedSortKey.length) {
    PropertyKey keyType=extendedSortKey[position];
    Interval interval=intervalConstraints.get(keyType);
    if (interval != null) {
      sortKeyConstraints[position]=new EdgeSerializer.TypedInterval(keyType,interval);
      position++;
    }
    if (interval != null && interval.isPoints()) {
      for (      Object point : interval.getPoints()) {
        EdgeSerializer.TypedInterval[] clonedSKC=Arrays.copyOf(sortKeyConstraints,sortKeyConstraints.length);
        clonedSKC[position - 1]=new EdgeSerializer.TypedInterval(keyType,new PointInterval(point));
        constructSliceQueries(extendedSortKey,clonedSKC,position,bestCandidate,direction,intervalConstraints,sliceLimit,isIntervalFittedConditions,bestCandidateSupportsOrder,queries);
      }
      return;
    }
  }
  boolean isFitted=isIntervalFittedConditions && position == intervalConstraints.size();
  if (isFitted && position > 0) {
    EdgeSerializer.TypedInterval lastInterval=sortKeyConstraints[position - 1];
    if (!lastInterval.interval.isPoints() && lastInterval.interval.getEnd() == null)     isFitted=false;
  }
  EdgeSerializer serializer=tx.getEdgeSerializer();
  SliceQuery q=serializer.getQuery(bestCandidate,direction,sortKeyConstraints);
  q.setLimit(computeLimit(intervalConstraints.size() - position,sliceLimit));
  queries.add(new BackendQueryHolder<>(q,isFitted,bestCandidateSupportsOrder));
}
