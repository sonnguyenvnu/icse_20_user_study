@Override public float predict(int[] observation,int[] state){
  final int time=observation.length;
  final int max_s=start_probability.length;
  float[][] score=new float[max_s][max_s];
  float[] first=new float[max_s];
  int[][][] link=new int[time][max_s][max_s];
  for (int cur_s=0; cur_s < max_s; ++cur_s) {
    first[cur_s]=start_probability[cur_s] + emission_probability[cur_s][observation[0]];
  }
  if (time == 1) {
    int best_s=0;
    float max_score=Integer.MIN_VALUE;
    for (int cur_s=0; cur_s < max_s; ++cur_s) {
      if (first[cur_s] > max_score) {
        best_s=cur_s;
        max_score=first[cur_s];
      }
    }
    state[0]=best_s;
    return max_score;
  }
  for (int f=0; f < max_s; ++f) {
    for (int s=0; s < max_s; ++s) {
      float p=first[f] + transition_probability[f][s] + emission_probability[s][observation[1]];
      score[f][s]=p;
      link[1][f][s]=f;
    }
  }
  float[][] pre=new float[max_s][max_s];
  for (int i=2; i < observation.length; i++) {
    float[][] _=pre;
    pre=score;
    score=_;
    for (int s=0; s < max_s; ++s) {
      for (int t=0; t < max_s; ++t) {
        score[s][t]=Integer.MIN_VALUE;
        for (int f=0; f < max_s; ++f) {
          float p=pre[f][s] + transition_probability2[f][s][t] + emission_probability[t][observation[i]];
          if (p > score[s][t]) {
            score[s][t]=p;
            link[i][s][t]=f;
          }
        }
      }
    }
  }
  float max_score=Integer.MIN_VALUE;
  int best_s=0, best_t=0;
  for (int s=0; s < max_s; s++) {
    for (int t=0; t < max_s; t++) {
      if (score[s][t] > max_score) {
        max_score=score[s][t];
        best_s=s;
        best_t=t;
      }
    }
  }
  for (int i=link.length - 1; i >= 0; --i) {
    state[i]=best_t;
    int best_f=link[i][best_s][best_t];
    best_t=best_s;
    best_s=best_f;
  }
  return max_score;
}
