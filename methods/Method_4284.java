private int findPitchPeriodInRange(short[] samples,int position,int minPeriod,int maxPeriod){
  int bestPeriod=0;
  int worstPeriod=255;
  int minDiff=1;
  int maxDiff=0;
  position*=channelCount;
  for (int period=minPeriod; period <= maxPeriod; period++) {
    int diff=0;
    for (int i=0; i < period; i++) {
      short sVal=samples[position + i];
      short pVal=samples[position + period + i];
      diff+=Math.abs(sVal - pVal);
    }
    if (diff * bestPeriod < minDiff * period) {
      minDiff=diff;
      bestPeriod=period;
    }
    if (diff * worstPeriod > maxDiff * period) {
      maxDiff=diff;
      worstPeriod=period;
    }
  }
  this.minDiff=minDiff / bestPeriod;
  this.maxDiff=maxDiff / worstPeriod;
  return bestPeriod;
}
