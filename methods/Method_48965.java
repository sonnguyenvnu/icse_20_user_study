protected BaseVertexCentricQuery constructQueryWithoutProfile(RelationCategory returnType){
  assert returnType != null;
  Preconditions.checkArgument(adjacentVertex == null || returnType == RelationCategory.EDGE,"Vertex constraints only apply to edges");
  if (limit <= 0)   return BaseVertexCentricQuery.emptyQuery();
  if (returnType == RelationCategory.PROPERTY) {
    if (dir == Direction.IN)     return BaseVertexCentricQuery.emptyQuery();
    dir=Direction.OUT;
  }
  orders.makeImmutable();
  assert orders.hasCommonOrder();
  And<JanusGraphRelation> conditions=QueryUtil.constraints2QNF(tx,constraints);
  if (conditions == null)   return BaseVertexCentricQuery.emptyQuery();
  int sliceLimit=limit;
  EdgeSerializer serializer=tx.getEdgeSerializer();
  List<BackendQueryHolder<SliceQuery>> queries;
  if (!hasTypes()) {
    final BackendQueryHolder<SliceQuery> query=new BackendQueryHolder<>(serializer.getQuery(returnType,querySystem),((dir == Direction.BOTH || (returnType == RelationCategory.PROPERTY && dir == Direction.OUT)) && !conditions.hasChildren()),orders.isEmpty());
    if (sliceLimit != Query.NO_LIMIT && sliceLimit < Integer.MAX_VALUE / 3) {
      if (dir != Direction.BOTH && (returnType == RelationCategory.EDGE || returnType == RelationCategory.RELATION)) {
        sliceLimit*=2;
      }
    }
    query.getBackendQuery().setLimit(computeLimit(conditions.size(),sliceLimit));
    queries=ImmutableList.of(query);
    conditions.add(returnType);
    conditions.add(new VisibilityFilterCondition<>(querySystem ? VisibilityFilterCondition.Visibility.SYSTEM : VisibilityFilterCondition.Visibility.NORMAL));
  }
 else {
    final Set<RelationType> ts=new HashSet<>(types.length);
    queries=new ArrayList<>(types.length + 2);
    final Map<RelationType,Interval> intervalConstraints=new HashMap<>(conditions.size());
    final boolean isIntervalFittedConditions=compileConstraints(conditions,intervalConstraints);
    for (    Interval pint : intervalConstraints.values()) {
      if (pint.isEmpty())       return BaseVertexCentricQuery.emptyQuery();
    }
    for (    String typeName : types) {
      InternalRelationType type=QueryUtil.getType(tx,typeName);
      if (type == null)       continue;
      Preconditions.checkArgument(!querySystem || (type instanceof SystemRelationType),"Can only query for system types: %s",type);
      if (type instanceof ImplicitKey) {
        throw new UnsupportedOperationException("Implicit types are not supported in complex queries: " + type);
      }
      ts.add(type);
      Direction typeDir=dir;
      if (type.isPropertyKey()) {
        Preconditions.checkArgument(returnType != RelationCategory.EDGE,"Querying for edges but including a property key: " + type.name());
        returnType=RelationCategory.PROPERTY;
        typeDir=Direction.OUT;
      }
      if (type.isEdgeLabel()) {
        Preconditions.checkArgument(returnType != RelationCategory.PROPERTY,"Querying for properties but including an edge label: " + type.name());
        returnType=RelationCategory.EDGE;
        if (!type.isUnidirected(Direction.BOTH)) {
          if (typeDir == Direction.BOTH) {
            if (type.isUnidirected(Direction.OUT))             typeDir=Direction.OUT;
 else             typeDir=Direction.IN;
          }
 else           if (!type.isUnidirected(typeDir))           continue;
        }
      }
      if (type.isEdgeLabel() && typeDir == Direction.BOTH && intervalConstraints.isEmpty() && orders.isEmpty()) {
        SliceQuery q=serializer.getQuery(type,typeDir,null);
        q.setLimit(sliceLimit);
        queries.add(new BackendQueryHolder<>(q,isIntervalFittedConditions,true));
      }
 else {
        Direction[] dirs={typeDir};
        if (typeDir == Direction.BOTH) {
          if (type.isEdgeLabel())           dirs=new Direction[]{Direction.OUT,Direction.IN};
 else           dirs=new Direction[]{Direction.OUT};
        }
        for (        Direction direction : dirs) {
          InternalRelationType bestCandidate=null;
          double bestScore=Double.NEGATIVE_INFINITY;
          boolean bestCandidateSupportsOrder=false;
          for (          InternalRelationType candidate : type.getRelationIndexes()) {
            if (!candidate.isUnidirected(Direction.BOTH) && !candidate.isUnidirected(direction)) {
              continue;
            }
            if (!candidate.equals(type) && candidate.getStatus() != SchemaStatus.ENABLED)             continue;
            boolean supportsOrder=orders.isEmpty() || orders.getCommonOrder() == candidate.getSortOrder();
            int currentOrder=0;
            double score=0.0;
            PropertyKey[] extendedSortKey=getExtendedSortKey(candidate,direction,tx);
            for (            PropertyKey keyType : extendedSortKey) {
              if (currentOrder < orders.size() && orders.getKey(currentOrder).equals(keyType))               currentOrder++;
              Interval interval=intervalConstraints.get(keyType);
              if (interval == null || !interval.isPoints()) {
                if (interval != null)                 score+=1;
                break;
              }
 else {
                assert interval.isPoints();
                score+=5.0 / interval.getPoints().size();
              }
            }
            if (supportsOrder && currentOrder == orders.size())             score+=3;
            if (score > bestScore) {
              bestScore=score;
              bestCandidate=candidate;
              bestCandidateSupportsOrder=supportsOrder && currentOrder == orders.size();
            }
          }
          Preconditions.checkArgument(bestCandidate != null,"Current graph schema does not support the specified query constraints for type: %s",type.name());
          PropertyKey[] extendedSortKey=getExtendedSortKey(bestCandidate,direction,tx);
          EdgeSerializer.TypedInterval[] sortKeyConstraints=new EdgeSerializer.TypedInterval[extendedSortKey.length];
          constructSliceQueries(extendedSortKey,sortKeyConstraints,0,bestCandidate,direction,intervalConstraints,sliceLimit,isIntervalFittedConditions,bestCandidateSupportsOrder,queries);
        }
      }
    }
    if (queries.isEmpty())     return BaseVertexCentricQuery.emptyQuery();
    conditions.add(getTypeCondition(ts));
  }
  return new BaseVertexCentricQuery(QueryUtil.simplifyQNF(conditions),dir,queries,orders,limit);
}
