public void initialize(int clusterSize){
  for (int factor=0; factor < numFactors; factor++) {
    for (int sample=0; sample < clusterSize; sample++) {
      FeatureVector data=(FeatureVector)instances.get(random.nextInt(numInstances)).getData();
      for (int location=0; location < data.numLocations(); location++) {
        int feature=data.indexAtLocation(location);
        double value=data.valueAtLocation(location);
        if (idfWeighting) {
          value*=featureWeights[feature];
        }
        featureFactorWeights[feature][factor]+=value / clusterSize;
        featureSums[factor]+=value / clusterSize;
      }
    }
  }
}
