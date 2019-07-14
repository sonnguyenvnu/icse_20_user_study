/** 
 * ????????
 * @param samples   ?????
 * @param max_state ???????
 * @param max_obser ???????
 */
protected void estimateEmissionProbability(Collection<int[][]> samples,int max_state,int max_obser){
  emission_probability=new float[max_state + 1][max_obser + 1];
  for (  int[][] sample : samples) {
    for (int i=0; i < sample[0].length; i++) {
      int o=sample[0][i];
      int s=sample[1][i];
      ++emission_probability[s][o];
    }
  }
  for (int i=0; i < transition_probability.length; i++)   normalize(emission_probability[i]);
}
