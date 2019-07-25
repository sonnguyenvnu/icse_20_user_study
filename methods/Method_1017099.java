@Override public FeatureSet match(QueryContext queryContext){
  FeatureSet features=FeatureSet.empty();
  for (  final ConditionalFeatures conditionalFeatures : list()) {
    features=features.combine(conditionalFeatures.match(queryContext));
  }
  return features;
}
