public double getPos(double t,int j){
  final int n=mT.length;
  if (t <= mT[0]) {
    return mY[0][j];
  }
  if (t >= mT[n - 1]) {
    return mY[n - 1][j];
  }
  for (int i=0; i < n - 1; i++) {
    if (t == mT[i]) {
      return mY[i][j];
    }
    if (t < mT[i + 1]) {
      double h=mT[i + 1] - mT[i];
      double x=(t - mT[i]) / h;
      double y1=mY[i][j];
      double y2=mY[i + 1][j];
      return (y1 * (1 - x) + y2 * x);
    }
  }
  return 0;
}
