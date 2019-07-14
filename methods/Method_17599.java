@Override public Adaptation adapt(double windowSize,double probationSize,double protectedSize,boolean isFull){
  if (sample <= period) {
    return Adaptation.hold();
  }
  long[] periodMisses=new long[101];
  for (int i=0; i < minis.length; i++) {
    periodMisses[i]=minis[i].stats().missCount() - prevMisses[i];
    prevMisses[i]=minis[i].stats().missCount();
  }
  int minIndex=0;
  for (int i=1; i < periodMisses.length; i++) {
    if (periodMisses[i] < periodMisses[minIndex]) {
      minIndex=i;
    }
  }
  sample=0;
  double oldPercent=prevPercent;
  double newPercent=prevPercent=minIndex < 80 ? minIndex / 100.0 : 0.8;
  return (newPercent > oldPercent) ? Adaptation.increaseWindow((int)((newPercent - oldPercent) * cacheSize)) : Adaptation.decreaseWindow((int)((oldPercent - newPercent) * cacheSize));
}
