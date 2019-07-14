@Override public Adaptation adapt(double windowSize,double probationSize,double protectedSize,boolean isFull){
  if (!isFull) {
    return Adaptation.hold();
  }
  checkState(sampleSize > 0,"Sample size may not be zero");
  int sampleCount=(hitsInSample + missesInSample);
  if (sampleCount < sampleSize) {
    return Adaptation.hold();
  }
  double hitRate=(double)hitsInSample / sampleCount;
  Adaptation adaption=Adaptation.adaptBy(adjust(hitRate));
  resetSample(hitRate);
  if (debug) {
    System.out.printf("%.2f\t%.2f%n",100 * hitRate,windowSize);
  }
  return adaption;
}
