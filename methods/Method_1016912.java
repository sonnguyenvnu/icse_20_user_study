public static double[] append(double[] original,double newValue){
  double[] ret=new double[original.length + 1];
  System.arraycopy(original,0,ret,0,original.length);
  ret[original.length]=newValue;
  return ret;
}
