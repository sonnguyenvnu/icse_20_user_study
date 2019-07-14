public double[] simAll(List<String> sentence){
  double[] scores=new double[D];
  for (int i=0; i < D; ++i) {
    scores[i]=sim(sentence,i);
  }
  return scores;
}
