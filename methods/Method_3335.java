/** 
 * ??
 * @param samples ??? int[i][j] i=0????i=1????j????
 */
public void train(Collection<int[][]> samples){
  if (samples.isEmpty())   return;
  int max_state=0;
  int max_obser=0;
  for (  int[][] sample : samples) {
    if (sample.length != 2 || sample[0].length != sample[1].length)     throw new IllegalArgumentException("????");
    for (    int o : sample[0])     max_obser=Math.max(max_obser,o);
    for (    int s : sample[1])     max_state=Math.max(max_state,s);
  }
  estimateStartProbability(samples,max_state);
  estimateTransitionProbability(samples,max_state);
  estimateEmissionProbability(samples,max_state,max_obser);
  toLog();
}
