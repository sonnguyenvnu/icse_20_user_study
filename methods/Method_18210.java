private byte getFirstAvailableIndex(){
  if (mValues == null) {
    mValues=new float[]{YogaConstants.UNDEFINED,YogaConstants.UNDEFINED};
    return 0;
  }
  for (int i=0; i < mValues.length; i++) {
    if (YogaConstants.isUndefined(mValues[i])) {
      return (byte)i;
    }
  }
  float[] oldValues=mValues;
  mValues=new float[Math.min(oldValues.length * 2,EDGES_LENGTH)];
  System.arraycopy(oldValues,0,mValues,0,oldValues.length);
  Arrays.fill(mValues,oldValues.length,mValues.length,YogaConstants.UNDEFINED);
  return (byte)oldValues.length;
}
