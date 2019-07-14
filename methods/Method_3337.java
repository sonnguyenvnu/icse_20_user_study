/** 
 * ????????????
 * @param samples   ?????
 * @param max_state ??????????N-1
 */
protected void estimateTransitionProbability(Collection<int[][]> samples,int max_state){
  transition_probability=new float[max_state + 1][max_state + 1];
  for (  int[][] sample : samples) {
    int prev_s=sample[1][0];
    for (int i=1; i < sample[0].length; i++) {
      int s=sample[1][i];
      ++transition_probability[prev_s][s];
      prev_s=s;
    }
  }
  for (int i=0; i < transition_probability.length; i++)   normalize(transition_probability[i]);
}
