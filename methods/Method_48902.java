public GraphCentricQuery constructQueryWithoutProfile(final ElementCategory resultType){
  Preconditions.checkNotNull(resultType);
  if (limit == 0)   return GraphCentricQuery.emptyQuery(resultType);
  final MultiCondition<JanusGraphElement> conditions;
  if (this.globalConstraints.size() == 1) {
    conditions=QueryUtil.constraints2QNF(tx,constraints);
    if (conditions == null)     return GraphCentricQuery.emptyQuery(resultType);
  }
 else {
    conditions=new Or<>();
    for (    final List<PredicateCondition<String,JanusGraphElement>> child : this.globalConstraints) {
      final And<JanusGraphElement> localconditions=QueryUtil.constraints2QNF(tx,child);
      if (localconditions == null)       return GraphCentricQuery.emptyQuery(resultType);
      conditions.add(localconditions);
    }
  }
  orders.makeImmutable();
  if (orders.isEmpty())   orders=OrderList.NO_ORDER;
  final Set<IndexType> indexCandidates=new HashSet<>();
  ConditionUtil.traversal(conditions,condition -> {
    if (condition instanceof PredicateCondition) {
      final RelationType type=((PredicateCondition<RelationType,JanusGraphElement>)condition).getKey();
      Preconditions.checkArgument(type != null && type.isPropertyKey());
      Iterables.addAll(indexCandidates,Iterables.filter(((InternalRelationType)type).getKeyIndexes(),indexType -> indexType.getElement() == resultType && !(conditions instanceof Or && (indexType.isCompositeIndex() || !serializer.features((MixedIndexType)indexType).supportNotQueryNormalForm()))));
    }
    return true;
  }
);
  final JointIndexQuery jointQuery=new JointIndexQuery();
  boolean isSorted=orders.isEmpty();
  final Set<Condition> coveredClauses=Sets.newHashSet();
  while (true) {
    IndexType bestCandidate=null;
    double candidateScore=0.0;
    Set<Condition> candidateSubcover=null;
    boolean candidateSupportsSort=false;
    Object candidateSubCondition=null;
    for (    final IndexType index : indexCandidates) {
      final Set<Condition> subcover=Sets.newHashSet();
      Object subCondition;
      boolean supportsSort=orders.isEmpty();
      if (index.hasSchemaTypeConstraint()) {
        final JanusGraphSchemaType type=index.getSchemaTypeConstraint();
        final Map.Entry<Condition,Collection<Object>> equalCon=getEqualityConditionValues(conditions,ImplicitKey.LABEL);
        if (equalCon == null)         continue;
        final Collection<Object> labels=equalCon.getValue();
        assert labels.size() >= 1;
        if (labels.size() > 1) {
          log.warn("The query optimizer currently does not support multiple label constraints in query: {}",this);
          continue;
        }
        if (!type.name().equals(Iterables.getOnlyElement(labels))) {
          continue;
        }
        subcover.add(equalCon.getKey());
      }
      if (index.isCompositeIndex()) {
        subCondition=indexCover((CompositeIndexType)index,conditions,subcover);
      }
 else {
        subCondition=indexCover((MixedIndexType)index,conditions,serializer,subcover);
        if (coveredClauses.isEmpty() && !supportsSort && indexCoversOrder((MixedIndexType)index,orders))         supportsSort=true;
      }
      if (subCondition == null || subcover.isEmpty())       continue;
      double score=0.0;
      boolean coversAdditionalClause=false;
      for (      final Condition c : subcover) {
        double s=(c instanceof PredicateCondition && ((PredicateCondition)c).getPredicate() == Cmp.EQUAL) ? EQUAL_CONDITION_SCORE : OTHER_CONDITION_SCORE;
        if (coveredClauses.contains(c))         s=s * ALREADY_MATCHED_ADJUSTOR;
 else         coversAdditionalClause=true;
        score+=s;
        if (index.isCompositeIndex())         score+=((CompositeIndexType)index).getCardinality() == Cardinality.SINGLE ? CARDINALITY_SINGE_SCORE : CARDINALITY_OTHER_SCORE;
      }
      if (supportsSort)       score+=ORDER_MATCH;
      if (coversAdditionalClause && score > candidateScore) {
        candidateScore=score;
        bestCandidate=index;
        candidateSubcover=subcover;
        candidateSubCondition=subCondition;
        candidateSupportsSort=supportsSort;
      }
    }
    if (bestCandidate != null) {
      if (coveredClauses.isEmpty())       isSorted=candidateSupportsSort;
      coveredClauses.addAll(candidateSubcover);
      if (bestCandidate.isCompositeIndex()) {
        jointQuery.add((CompositeIndexType)bestCandidate,serializer.getQuery((CompositeIndexType)bestCandidate,(List<Object[]>)candidateSubCondition));
      }
 else {
        jointQuery.add((MixedIndexType)bestCandidate,serializer.getQuery((MixedIndexType)bestCandidate,(Condition)candidateSubCondition,orders));
      }
    }
 else {
      break;
    }
  }
  BackendQueryHolder<JointIndexQuery> query;
  if (!coveredClauses.isEmpty()) {
    int indexLimit=limit == Query.NO_LIMIT ? HARD_MAX_LIMIT : limit;
    if (tx.getGraph().getConfiguration().adjustQueryLimit()) {
      indexLimit=limit == Query.NO_LIMIT ? DEFAULT_NO_LIMIT : Math.min(MAX_BASE_LIMIT,limit);
    }
    indexLimit=Math.min(HARD_MAX_LIMIT,QueryUtil.adjustLimitForTxModifications(tx,coveredClauses.size(),indexLimit));
    jointQuery.setLimit(indexLimit);
    query=new BackendQueryHolder<>(jointQuery,coveredClauses.size() == conditions.numChildren(),isSorted);
  }
 else {
    query=new BackendQueryHolder<>(new JointIndexQuery(),false,isSorted);
  }
  return new GraphCentricQuery(resultType,conditions,orders,query,limit);
}
