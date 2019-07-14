public double getSlope(double t,int j){
  final int n=mT.length;
  if (t < mT[0]) {
    t=mT[0];
  }
 else   if (t >= mT[n - 1]) {
    t=mT[n - 1];
  }
  for (int i=0; i < n - 1; i++) {
    if (t <= mT[i + 1]) {
      double h=mT[i + 1] - mT[i];
      double x=(t - mT[i]) / h;
      double y1=mY[i][j];
      double y2=mY[i + 1][j];
      return (y2 - y1) / h;
    }
  }
  return 0;
}
