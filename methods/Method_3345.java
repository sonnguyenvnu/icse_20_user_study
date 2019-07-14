@Override protected void estimateTransitionProbability(Collection<int[][]> samples,int max_state){
  transition_probability=new float[max_state + 1][max_state + 1];
  transition_probability2=new float[max_state + 1][max_state + 1][max_state + 1];
  for (  int[][] sample : samples) {
    int prev_s=sample[1][0];
    int prev_prev_s=-1;
    for (int i=1; i < sample[0].length; i++) {
      int s=sample[1][i];
      if (i == 1)       ++transition_probability[prev_s][s];
 else       ++transition_probability2[prev_prev_s][prev_s][s];
      prev_prev_s=prev_s;
      prev_s=s;
    }
  }
  for (  float[] p : transition_probability)   normalize(p);
  for (  float[][] pp : transition_probability2)   for (  float[] p : pp)   normalize(p);
}
