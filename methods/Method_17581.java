@Override protected void resetSample(double hitRate){
  super.resetSample(hitRate);
  stepSize*=stepDecayRate;
  sampleSize=(int)(sampleSize * sampleDecayRate);
  if ((stepSize <= 0.01) || (sampleSize <= 1)) {
    sampleSize=Integer.MAX_VALUE;
  }
}
