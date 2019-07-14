private double[][][] logToCdf(float[][][] log){
  double[][][] cdf=new double[log.length][log[0].length][log[0][0].length];
  for (int i=0; i < log.length; i++) {
    cdf[i]=logToCdf(log[i]);
  }
  return cdf;
}
