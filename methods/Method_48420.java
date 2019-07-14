public double[] getDoubles(int position,int length){
  double[] result=new double[length];
  for (int i=0; i < length; i++) {
    result[i]=getDouble(position);
    position+=DOUBLE_LEN;
  }
  return result;
}
