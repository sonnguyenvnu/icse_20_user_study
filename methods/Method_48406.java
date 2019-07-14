public double[] getDoubles(int length){
  double[] result=super.getDoubles(position,length);
  position+=length * DOUBLE_LEN;
  return result;
}
