public double evaluate(Neighbor neighbor){
  return classifier.classify(neighbor).getLabelVector().value(scoringLabel);
}
