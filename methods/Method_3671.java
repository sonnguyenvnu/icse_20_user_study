public static void normalizeExp(double[] predictionScores){
  double max=Double.NEGATIVE_INFINITY;
  for (  double value : predictionScores) {
    max=Math.max(max,value);
  }
  double sum=0.0;
  for (int i=0; i < predictionScores.length; i++) {
    predictionScores[i]=Math.exp(predictionScores[i] - max);
    sum+=predictionScores[i];
  }
  if (sum != 0.0) {
    for (int i=0; i < predictionScores.length; i++) {
      predictionScores[i]/=sum;
    }
  }
}
