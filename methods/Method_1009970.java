@SuppressWarnings("WeakerAccess") public static double llr(double[][] count){
  if (count.length == 0) {
    throw new IllegalArgumentException("Must have some data in llr");
  }
  int columns=count[0].length;
  int rows=count.length;
  double[] rowSums=new double[rows];
  double[] colSums=new double[columns];
  double totalCount=0;
  double h=0;
  for (int i=0; i < rows; i++) {
    for (int j=0; j < columns; j++) {
      double k=count[i][j];
      rowSums[i]+=k;
      colSums[j]+=k;
      if (k < 0) {
        throw new IllegalArgumentException(String.format("Illegal negative count (%.5f) at %d,%d",k,i,j));
      }
      if (k > 0) {
        h+=k * Math.log(k);
        totalCount+=k;
      }
    }
  }
  double normalizer=totalCount * Math.log(totalCount);
  h-=normalizer;
  double hr=0;
  for (int i=0; i < rows; i++) {
    if (rowSums[i] > 0) {
      hr+=rowSums[i] * Math.log(rowSums[i]);
    }
  }
  hr-=normalizer;
  double hc=0;
  for (int j=0; j < columns; j++) {
    if (colSums[j] > 0) {
      hc+=colSums[j] * Math.log(colSums[j]);
    }
  }
  hc-=normalizer;
  return 2 * (h - hr - hc);
}
