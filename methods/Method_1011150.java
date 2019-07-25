public static Reference<Double> create(double[] array,int index){
  return new ArrayElementReference._double(array,index);
}
