@Override public Adaptation adapt(double windowSize,double probationSize,double protectedSize,boolean isFull){
  if (indicator.getSample() != 50_000) {
    return Adaptation.hold();
  }
  double newPercent=(indicator.getIndicator() * 80) / 100.0;
  double oldPercent=prevPercent;
  prevPercent=newPercent;
  indicator.reset();
  return (newPercent > oldPercent) ? Adaptation.increaseWindow((int)((newPercent - oldPercent) * cacheSize)) : Adaptation.decreaseWindow((int)((oldPercent - newPercent) * cacheSize));
}
