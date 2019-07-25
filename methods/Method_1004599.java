public void set(final long pXMin,final long pYMin,final long pXMax,final long pYMax,final PointAccepter pPointAccepter,final boolean pPathMode){
  mXMin=pXMin;
  mYMin=pYMin;
  mXMax=pXMax;
  mYMax=pYMax;
  cornerX[0]=cornerX[1]=mXMin;
  cornerX[2]=cornerX[3]=mXMax;
  cornerY[0]=cornerY[2]=mYMin;
  cornerY[1]=cornerY[3]=mYMax;
  mPointAccepter=pPointAccepter;
  mPathMode=pPathMode;
}
