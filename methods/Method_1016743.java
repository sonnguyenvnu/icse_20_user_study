public String evaluate(Clustering truth,Clustering predicted){
  return "accuracy=" + String.valueOf(getEvaluationScores(truth,predicted)[0]);
}
