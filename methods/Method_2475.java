public ChiSquareFeatureExtractor setALevel(double aLevel){
  chisquareCriticalValue=ContinuousDistributions.ChisquareInverseCdf(aLevel,1);
  return this;
}
