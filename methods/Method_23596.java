public DoubleList getSubset(int start,int num){
  double[] subset=new double[num];
  System.arraycopy(data,start,subset,0,num);
  return new DoubleList(subset);
}
