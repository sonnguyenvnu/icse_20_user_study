@Override public Classification classify(Instance instance){
  double[] scores=new double[numLabels];
  getClassificationScores(instance,scores);
  return new Classification(instance,this,new LabelVector(getLabelAlphabet(),scores));
}
