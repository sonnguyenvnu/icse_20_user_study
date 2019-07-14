static public double[] subset(double[] list,int start,int count){
  double[] output=new double[count];
  System.arraycopy(list,start,output,0,count);
  return output;
}
