@Override public float predict(int[] observation,int[] state){
  final int time=observation.length;
  final int max_s=start_probability.length;
  float[] score=new float[max_s];
  int[][] link=new int[time][max_s];
  for (int cur_s=0; cur_s < max_s; ++cur_s) {
    score[cur_s]=start_probability[cur_s] + emission_probability[cur_s][observation[0]];
  }
  float[] pre=new float[max_s];
  for (int t=1; t < observation.length; t++) {
    float[] _=pre;
    pre=score;
    score=_;
    for (int s=0; s < max_s; ++s) {
      score[s]=Integer.MIN_VALUE;
      for (int f=0; f < max_s; ++f) {
        float p=pre[f] + transition_probability[f][s] + emission_probability[s][observation[t]];
        if (p > score[s]) {
          score[s]=p;
          link[t][s]=f;
        }
      }
    }
  }
  float max_score=Integer.MIN_VALUE;
  int best_s=0;
  for (int s=0; s < max_s; s++) {
    if (score[s] > max_score) {
      max_score=score[s];
      best_s=s;
    }
  }
  for (int t=link.length - 1; t >= 0; --t) {
    state[t]=best_s;
    best_s=link[t][best_s];
  }
  return max_score;
}
