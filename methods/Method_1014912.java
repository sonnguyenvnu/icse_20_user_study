public static double[] filter(double[] samples,double[] coeffs){
  double[] newSamples=new double[samples.length];
  double[] xv=new double[3];
  double[] yv=new double[3];
  for (int i=0; i < samples.length; i++) {
    xv[2]=xv[1];
    xv[1]=xv[0];
    xv[0]=samples[i];
    yv[2]=yv[1];
    yv[1]=yv[0];
    yv[0]=(coeffs[0] * xv[0] + coeffs[1] * xv[1] + coeffs[2] * xv[2] - coeffs[4] * yv[0] - coeffs[5] * yv[1]);
    newSamples[i]=yv[0];
  }
  return newSamples;
}
