public double[] variances(){
  double[] means=new double[numColumns];
  for (int word=0; word < numWords; word++) {
    for (int col=0; col < numColumns; col++) {
      means[col]+=weights[word * stride + col];
    }
  }
  for (int col=0; col < numColumns; col++) {
    means[col]/=numWords;
  }
  double[] squaredSums=new double[numColumns];
  double diff;
  for (int word=0; word < numWords; word++) {
    for (int col=0; col < numColumns; col++) {
      diff=weights[word * stride + col] - means[col];
      squaredSums[col]+=diff * diff;
    }
  }
  for (int col=0; col < numColumns; col++) {
    squaredSums[col]/=(numWords - 1);
    System.out.format("%f\t",squaredSums[col]);
  }
  System.out.println();
  return squaredSums;
}
