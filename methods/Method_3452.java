/** 
 * ????
 * @param charArray ????
 * @return ????
 */
public char[] tag(char[] charArray){
  if (charArray.length == 0)   return new char[0];
  if (charArray.length == 1)   return new char[]{'s'};
  char[] tag=new char[charArray.length];
  double[][] now=new double[4][4];
  double[] first=new double[4];
  int[][][] link=new int[charArray.length][4][4];
  for (int s=0; s < 4; ++s) {
    double p=(s == 1 || s == 2) ? inf : log_prob(bos[0],4,bos[0],4,charArray[0],s);
    first[s]=p;
  }
  for (int f=0; f < 4; ++f) {
    for (int s=0; s < 4; ++s) {
      double p=first[f] + log_prob(bos[0],4,charArray[0],f,charArray[1],s);
      now[f][s]=p;
      link[1][f][s]=f;
    }
  }
  double[][] pre=new double[4][4];
  for (int i=2; i < charArray.length; i++) {
    double[][] _=pre;
    pre=now;
    now=_;
    for (int s=0; s < 4; ++s) {
      for (int t=0; t < 4; ++t) {
        now[s][t]=-1e20;
        for (int f=0; f < 4; ++f) {
          double p=pre[f][s] + log_prob(charArray[i - 2],f,charArray[i - 1],s,charArray[i],t);
          if (p > now[s][t]) {
            now[s][t]=p;
            link[i][s][t]=f;
          }
        }
      }
    }
  }
  double score=charArray.length * inf;
  int s=0;
  int t=0;
  for (int i=0; i < probableTail.length; i++) {
    int[] state=probableTail[i];
    if (now[state[0]][state[1]] > score) {
      score=now[state[0]][state[1]];
      s=state[0];
      t=state[1];
    }
  }
  for (int i=link.length - 1; i >= 0; --i) {
    tag[i]=id2tag[t];
    int f=link[i][s][t];
    t=s;
    s=f;
  }
  return tag;
}
