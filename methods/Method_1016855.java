public double predict(Instance instance){
  double prediction=parameters[interceptIndex];
  FeatureVector predictors=(FeatureVector)instance.getData();
  for (int location=0; location < predictors.numLocations(); location++) {
    int index=predictors.indexAtLocation(location);
    prediction+=parameters[index] * predictors.valueAtLocation(location);
  }
  return prediction;
}
