@Override public GraphCentricQueryBuilder has(String key,JanusGraphPredicate predicate,Object condition){
  Preconditions.checkNotNull(key);
  Preconditions.checkNotNull(predicate);
  Preconditions.checkArgument(predicate.isValidCondition(condition),"Invalid condition: %s",condition);
  if (predicate.equals(Contain.NOT_IN)) {
    has(key);
  }
  constraints.add(new PredicateCondition<>(key,predicate,condition));
  return this;
}
