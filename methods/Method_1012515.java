void reset(int spc,int sp,boolean full){
  int oldSpc=scopePointCount;
  scopePointCount=spc;
  if (speed != sp)   oldSpc=0;
  speed=sp;
  double oldMin[]=minValues;
  double oldMax[]=maxValues;
  minValues=new double[scopePointCount];
  maxValues=new double[scopePointCount];
  if (oldMin != null && !full) {
    int i;
    for (i=0; i != scopePointCount && i != oldSpc; i++) {
      int i1=(-i) & (scopePointCount - 1);
      int i2=(ptr - i) & (oldSpc - 1);
      minValues[i1]=oldMin[i2];
      maxValues[i1]=oldMax[i2];
    }
  }
 else   ctr=0;
  ptr=0;
}
