@Override public int[][] generate(int length){
  double[] pi=logToCdf(start_probability);
  double[][] A=logToCdf(transition_probability);
  double[][][] A2=logToCdf(transition_probability2);
  double[][] B=logToCdf(emission_probability);
  int os[][]=new int[2][length];
  os[1][0]=drawFrom(pi);
  os[0][0]=drawFrom(B[os[1][0]]);
  os[1][1]=drawFrom(A[os[1][0]]);
  os[0][1]=drawFrom(B[os[1][1]]);
  for (int t=2; t < length; t++) {
    os[1][t]=drawFrom(A2[os[1][t - 2]][os[1][t - 1]]);
    os[0][t]=drawFrom(B[os[1][t]]);
  }
  return os;
}
