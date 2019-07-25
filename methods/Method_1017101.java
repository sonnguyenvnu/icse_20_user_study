@Override public FeatureSet match(QueryContext queryContext){
  if (condition().matches(queryContext)) {
    return features();
  }
  return FeatureSet.empty();
}
