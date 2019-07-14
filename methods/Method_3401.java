public void average(double[] total,int[] timestamp,int current){
  for (int i=0; i < parameter.length; i++) {
    parameter[i]=(float)((total[i] + (current - timestamp[i]) * parameter[i]) / current);
  }
}
