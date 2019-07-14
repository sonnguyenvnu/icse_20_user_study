public static double[] prf(int goldTotal,int predTotal,int correct){
  double precision=(correct * 100.0) / predTotal;
  double recall=(correct * 100.0) / goldTotal;
  double[] performance=new double[3];
  performance[0]=precision;
  performance[1]=recall;
  performance[2]=(2 * precision * recall) / (precision + recall);
  return performance;
}
