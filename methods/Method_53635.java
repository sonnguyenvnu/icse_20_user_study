public void getPos(double p,float[] x){
  double pos=p * mTotalLength;
  double sum=0;
  int k=0;
  for (; k < mCurveLength.length - 1 && mCurveLength[k] < pos; k++) {
    pos-=mCurveLength[k];
  }
  for (int i=0; i < x.length; i++) {
    x[i]=(float)mCurve[i][k].eval(pos / mCurveLength[k]);
  }
}
