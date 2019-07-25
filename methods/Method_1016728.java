public Classification classify(Instance instance){
  int numClasses=getLabelAlphabet().size();
  double[] scores=new double[numClasses];
  getClassificationScores(instance,scores);
  return new Classification(instance,this,new LabelVector(getLabelAlphabet(),scores));
}
