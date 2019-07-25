public double[] shift(double[] conv){
  double newArray[]=new double[conv.length];
  for (int i=0; i < conv.length / 2; i++) {
    newArray[i]=conv[conv.length / 2 + i];
    newArray[conv.length / 2 + i]=conv[i];
  }
  return newArray;
}
