@Override public double[] categorize(Document document) throws IllegalArgumentException, IllegalStateException {
  Integer category;
  Integer feature;
  Integer occurrences;
  Double logprob;
  double[] predictionScores=new double[model.catalog.length];
  for (  Map.Entry<Integer,Double> entry1 : model.logPriors.entrySet()) {
    category=entry1.getKey();
    logprob=entry1.getValue();
    for (    Map.Entry<Integer,int[]> entry2 : document.tfMap.entrySet()) {
      feature=entry2.getKey();
      if (!model.logLikelihoods.containsKey(feature)) {
        continue;
      }
      occurrences=entry2.getValue()[0];
      logprob+=occurrences * model.logLikelihoods.get(feature).get(category);
    }
    predictionScores[category]=logprob;
  }
  if (configProbabilityEnabled)   MathUtility.normalizeExp(predictionScores);
  return predictionScores;
}
