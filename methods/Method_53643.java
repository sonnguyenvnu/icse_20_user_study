@Override public void getSlope(double t,double[] v){
  final int n=mT.length;
  int dim=mY[0].length;
  if (t <= mT[0]) {
    t=mT[0];
  }
 else   if (t >= mT[n - 1]) {
    t=mT[n - 1];
  }
  for (int i=0; i < n - 1; i++) {
    if (t <= mT[i + 1]) {
      double h=mT[i + 1] - mT[i];
      double x=(t - mT[i]) / h;
      for (int j=0; j < dim; j++) {
        double y1=mY[i][j];
        double y2=mY[i + 1][j];
        double t1=mTangent[i][j];
        double t2=mTangent[i + 1][j];
        v[j]=diff(h,x,y1,y2,t1,t2) / h;
      }
      break;
    }
  }
  return;
}
