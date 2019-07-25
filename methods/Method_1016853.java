public Instance pipe(Instance instance){
  TokenSequence sequence=(TokenSequence)instance.getData();
  Token token=sequence.get(0);
  token.setFeatureValue(featureName,1.0);
  return instance;
}
